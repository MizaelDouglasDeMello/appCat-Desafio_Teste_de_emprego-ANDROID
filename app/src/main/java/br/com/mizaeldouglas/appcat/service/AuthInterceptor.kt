package br.com.mizaeldouglas.appcat.service

import br.com.mizaeldouglas.appcat.service.RetrofitInstance.CLIENT_ID
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.http.Query


class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("q", "cats")
            .addQueryParameter("sort", "all")
            .addQueryParameter("q_type", "png")
            .build()

        val requestWithAuth = originalRequest.newBuilder()
            .url(newUrl)
            .header("Authorization", "Client-ID $CLIENT_ID")
            .build()

        return chain.proceed(requestWithAuth)
    }
}