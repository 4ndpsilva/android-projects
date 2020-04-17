package br.com.myfinances.rest

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MobileApi {
    @GET("categories")
    suspend fun getAll(): Response<List<CategoryResponse>>

    companion object{
        private const val BASE_URL = "http://192.168.0.3:3000/"

        operator fun invoke(): MobileApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MobileApi::class.java)
        }
    }
}