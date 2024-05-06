package com.gkp.home.data

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("apiKey",apiKey)
        return chain.proceed(request.build())
    }

}