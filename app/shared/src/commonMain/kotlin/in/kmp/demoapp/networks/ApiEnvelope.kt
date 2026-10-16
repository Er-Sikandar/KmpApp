package `in`.kmp.demoapp.networks

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class ApiEnvelope(
    val code: Int? = null,
    val message: String? = null,
    val data: JsonElement? = null
)

val appJson = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = true
    explicitNulls = false
}