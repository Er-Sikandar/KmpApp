package `in`.kmp.demoapp.networks

import `in`.kmp.demoapp.utils.Const.ACCESS
import `in`.kmp.demoapp.utils.Const.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import io.ktor.http.content.TextContent
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.serializer

fun createHttpClient(engine: HttpClientEngine, localStorage: LocalStorage) =
    HttpClient(engine) {
        install(ContentNegotiation) { json(appJson) }
        defaultRequest { url(BASE_URL) }
        install(Auth) {
            bearer {
                loadTokens {
                    localStorage.getString(ACCESS)?.let { BearerTokens(it, "") }
                }
                sendWithoutRequest { true }
            }
        }
    }

private fun httpMessage(code: Int): String = when (code) {
    400 -> "Invalid request"
    401 -> "Session expired. Please login again"
    403 -> "Access denied"
    404 -> "Not found"
    408 -> "Request timed out"
    422 -> "Invalid input"
    429 -> "Too many requests. Try again later"
    in 500..599 -> "Server error. Try again later"
    else -> "Something went wrong ($code)"
}

suspend fun <T> HttpClient.callApi(serializer: KSerializer<T>?, method: HttpMethod, path: String,
                                   configure: HttpRequestBuilder.() -> Unit): ApiResult<T> = try {
    val response = request {
        this.method = method
        url(path)
        configure()
    }
    parseApiResponse(response, serializer)
} catch (e: CancellationException) {
    throw e
} catch (e: HttpRequestTimeoutException) {
    ApiResult.Error("Request timed out. Please try again.")
}  catch (e: ConnectTimeoutException) {
    ApiResult.Error("Request timed out. Please try again.")
} catch (e: SocketTimeoutException) {
    ApiResult.Error("Request timed out. Please try again.")
} catch (e: Exception) {
    ApiResult.Error("Network error. Check your connection.")
}

@Suppress("UNCHECKED_CAST")
suspend fun <T> parseApiResponse(response: HttpResponse, serializer: KSerializer<T>?): ApiResult<T> {
    val httpCode = response.status.value
    val envelope = try {
        appJson.decodeFromString(ApiEnvelope.serializer(), response.bodyAsText())
    } catch (e: Exception) {
        println("parseApiResponse envelope error: $e")
        null
    }
    if (envelope == null) {
        return ApiResult.Error(
            if (response.status.isSuccess()) "Unexpected server response" else httpMessage(httpCode)
        )
    }
    val envelopeOk = envelope.code == null || envelope.code in 200..299
    if (!response.status.isSuccess() || !envelopeOk) {
        val code = envelope.code?.takeIf { it !in 200..299 } ?: httpCode
        return ApiResult.Error(envelope.message?.takeIf { it.isNotBlank() } ?: httpMessage(code))
    }
    // Endpoints with no payload (e.g. delete) -> success
    if (serializer == null || serializer.descriptor.serialName == "kotlin.Unit") {
        return ApiResult.Success(Unit as T)
    }
    val nullable = serializer.descriptor.isNullable
    val data = envelope.data
    if (data == null || data is JsonNull) {
        return if (nullable) ApiResult.Success(null as T)
        else ApiResult.Error(envelope.message ?: "No data received")
    }
    return try {
        ApiResult.Success(appJson.decodeFromJsonElement(serializer, data))
    } catch (e: Exception) {
        println("parseApiResponse decode error: $e")
        if (data is JsonArray && data.isEmpty()) {
            if (nullable) ApiResult.Success(null as T)
            else ApiResult.Error(envelope.message ?: "No data found")
        } else {
            ApiResult.Error("Invalid data received from server")
        }
    }
}

/* ---------- wrappers used by API classes ---------- */

suspend inline fun <reified T> HttpClient.apiGet(
    path: String,
    noinline configure: HttpRequestBuilder.() -> Unit={}
): ApiResult<T> = callApi(serializer<T>(), HttpMethod.Get, path, configure)

suspend inline fun <reified T> HttpClient.apiPostForm(
    path: String,
    params: Map<String, Any?>,
    noinline configure: HttpRequestBuilder.() -> Unit={}
): ApiResult<T> = callApi(serializer<T>(), HttpMethod.Post, path) {
    contentType(ContentType.Application.FormUrlEncoded)
    setBody(
        FormDataContent(
            Parameters.build {
                params.forEach { (key, value) ->
                    when (value) {
                        null -> Unit
                        is Iterable<*> -> value.forEach { item ->
                            if (item != null) append(key, item.toString())
                        }
                        else -> append(key, value.toString())
                    }
                }
            }
        )
    )
    configure()
}

fun toJson(value: Any?): JsonElement = when (value) {
    null -> JsonNull
    is JsonElement -> value
    is String -> JsonPrimitive(value)
    is Boolean -> JsonPrimitive(value)
    is Number -> JsonPrimitive(value)
    is Enum<*> -> JsonPrimitive(value.name)
    is Map<*, *> -> JsonObject(value.entries.associate { (k, v) -> k.toString() to toJson(v) })
    is Iterable<*> -> JsonArray(value.map { toJson(it) })
    is Array<*> -> JsonArray(value.map { toJson(it) })
    else -> JsonPrimitive(value.toString())
}
suspend inline fun <reified T> HttpClient.apiPostRaw(
    path: String,
    params: Map<String, Any?>,
    noinline configure: HttpRequestBuilder.() -> Unit = {}
): ApiResult<T> {
    val json = toJson(params).toString()
    return callApi(serializer<T>(), HttpMethod.Post, path) {
        setBody(TextContent(json, ContentType.Application.Json))
        configure()
    }
}

suspend inline fun <reified T, reified B : Any> HttpClient.apiPut(
    path: String,
    body: B,
    noinline configure: HttpRequestBuilder.() -> Unit={}
): ApiResult<T> = callApi(serializer<T>(), HttpMethod.Put, path) {
    contentType(ContentType.Application.Json)
    setBody(body)
    configure()
}

suspend inline fun <reified T> HttpClient.apiDelete(
    path: String,
    noinline configure: HttpRequestBuilder.() -> Unit={}
): ApiResult<T> = callApi(serializer<T>(), HttpMethod.Delete, path, configure)
