package com.example.domain.mapper

import com.example.domain.model.error.HttpError
import com.example.domain.model.error.NetworkError
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class HttpErrorMapper @Inject constructor(){

    fun mapToErrorModel(throwable: Throwable): NetworkError? {
        return when (throwable) {
            is HttpException -> {
                getHttpError(throwable)
            }
            is SocketTimeoutException -> {
                HttpError.TimeOut
            }
            is IOException -> {
                HttpError.ConnectionFailed
            }
            else -> null
        }
    }

    private fun getHttpError(httpException: HttpException): NetworkError {
        return when (val code = httpException.code()) {
            401 -> HttpError.UnAuthorized
            else -> {
                val errorBody = httpException.response()?.errorBody()
                HttpError.InvalidResponse(code, errorBody?.string())
            }
        }
    }
}