package com.kuzmin.fooddeliverytestapp.data.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor @Inject constructor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("Authorization", "Token ${NetConstants.API_KEY}")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}