package com.example.domain.mapper

import com.example.domain.model.error.NetworkError
import javax.inject.Inject

class ErrorMapper @Inject constructor(private val httpErrorMapper: HttpErrorMapper) {

    /**
     * Generate an instance of [NetworkError] from happened [Throwable]
     * @param t Raised [Throwable]
     *
     * @return returns an instance of [NetworkError]
     */
    fun getError(t: Throwable): NetworkError {
        // if connection was successful but no data received
        if (t is NullPointerException) {
            return NetworkError.Null
        }
        val httpError = httpErrorMapper.mapToErrorModel(t)
        if (httpError != null) {
            return httpError
        }
        // something happened that we did not make our self ready for it
        return NetworkError.NotDefined(t)
    }
}