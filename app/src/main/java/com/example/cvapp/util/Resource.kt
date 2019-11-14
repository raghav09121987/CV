package com.example.cvapp.util


sealed class Resource<T>(val status: AuthStatus, val data: T? = null, val message: String? = null) {
    enum class AuthStatus {
        SUCCESS, ERROR, LOADING
    }
    class Success<T>(data: T) : Resource<T>(AuthStatus.SUCCESS, data)
    class Loading<T>(data: T? = null) : Resource<T>(AuthStatus.LOADING, data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(AuthStatus.ERROR, data, message)
}