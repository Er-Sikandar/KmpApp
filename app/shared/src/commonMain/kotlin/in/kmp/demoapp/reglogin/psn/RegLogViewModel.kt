package `in`.kmp.demoapp.reglogin.psn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import `in`.kmp.demoapp.networks.LocalStorage
import `in`.kmp.demoapp.reglogin.domain.AuthRepository
import `in`.kmp.demoapp.reglogin.domain.LoginEvent
import `in`.kmp.demoapp.reglogin.domain.LoginUiState
import `in`.kmp.demoapp.reglogin.domain.RegEvent
import `in`.kmp.demoapp.reglogin.domain.RegUiState
import `in`.kmp.demoapp.utils.Validator
import `in`.kmp.demoapp.networks.ApiResult
import `in`.kmp.demoapp.utils.Const.ACCESS
import `in`.kmp.demoapp.utils.Const.IS_LOGIN
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegLogViewModel(
    private val repo: AuthRepository,
    private val tokens: LocalStorage) : ViewModel() {
    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    private val _regState = MutableStateFlow(RegUiState())
    val regState = _regState.asStateFlow()

    fun onLoginEvent(e: LoginEvent) = when (e) {
        is LoginEvent.EmailChanged -> _state.update { it.copy(email = e.value, error = null) }
        is LoginEvent.PasswordChanged -> _state.update { it.copy(password = e.value, error = null) }
        is LoginEvent.Submit -> logSubmit()
        is LoginEvent.IsLoginFalse -> _state.update { it.copy(isLoggedIn = false) }
    }

    fun onRegEvent(e: RegEvent) = when (e) {
        is RegEvent.FullNameChanged -> _regState.update {
            it.copy(
                fullNane = e.value,
                error = null
            )
        }

        is RegEvent.PhoneChanged -> _regState.update { it.copy(phone = e.value, error = null) }
        is RegEvent.EmailChanged -> _regState.update { it.copy(email = e.value, error = null) }
        is RegEvent.PasswordChanged -> _regState.update {
            it.copy(
                password = e.value,
                error = null
            )
        }
        RegEvent.Submit -> regSubmit()
        is RegEvent.IsRegFalse -> _regState.update { it.copy(registered = false) }
    }

    private fun logSubmit() {
        val lState=state.value
        if (lState.isLoading) return
        val emailErr = Validator.emailError(lState.email)
        val passErr = Validator.passwordError(lState.password)
        if (emailErr != null || passErr != null) {
            _state.update { it.copy(emailError = emailErr, passwordError = passErr) }
            return
        }
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true, emailError = null, passwordError = null, error = null)
            }
            tokens.putBool(IS_LOGIN, true)
            _state.update { it.copy(isLoading = false, isLoggedIn = true) }
          /*  when (val result = repo.login(lState.email, lState.password)) {
                is ApiResult.Success -> {
                    val token = result.data?.token
                    if (token.isNullOrBlank()) {
                        _state.update {
                            it.copy(isLoading = false, error = "Login failed. No token received")
                        }
                    } else {
                        tokens.putString(ACCESS, token)
                        tokens.putBool(IS_LOGIN, true)
                        _state.update { it.copy(isLoading = false, isLoggedIn = true) }
                    }
                }

                is ApiResult.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.message) }
                }
            }*/
        }

    }

    private fun regSubmit() {
        val rState=regState.value
        if (rState.isLoading) return
        val fulNameErr = Validator.fulNameError(rState.fullNane)
        val phoneErr = Validator.phoneError(rState.phone)
        val emailErr = Validator.emailError(rState.email)
        val passErr = Validator.passwordError(rState.password)
        if (fulNameErr != null || phoneErr != null || emailErr != null || passErr != null) {
            _regState.update {
                it.copy(nameError = fulNameErr, phoneError = phoneErr, emailError = emailErr, passwordError = passErr)
            }
            return
        }

        viewModelScope.launch {
            _regState.update {
                it.copy(isLoading = true, nameError = null, phoneError = null, emailError = null, passwordError = null)
            }
            when (val result = repo.reg(rState.fullNane, rState.phone, rState.email, rState.password)) {
                is ApiResult.Success ->{
                    _regState.update {
                        it.copy(isLoading = false, registered = true, error=null)
                    }
                }
                is ApiResult.Error -> {
                    _regState.update {
                        it.copy(isLoading = false, registered = false, error=result.message)
                    }
                }
            }
        }
    }
    fun clearLogError() {
        _state.value = _state.value.copy(error = null)
    }
    fun clearRegError() {
        _regState.value = _regState.value.copy(error = null)
    }

}