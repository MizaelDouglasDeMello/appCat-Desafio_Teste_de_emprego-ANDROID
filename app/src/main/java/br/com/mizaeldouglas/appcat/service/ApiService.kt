package br.com.mizaeldouglas.appcat.service

import br.com.mizaeldouglas.appcat.model.ImgurResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImgurApi {
    @GET("3/gallery/search")
    suspend fun searchCats(
    ): Response<ImgurResponse>
}
