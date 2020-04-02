package br.com.myfinances

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MobileApi {
    @GET
    suspend fun getAll(): Response<List<CategoryResponse>>

    companion object{
        operator fun invoke(): MobileApi{
            return Retrofit.Builder()
                .baseUrl("http://192.168.0.2:3000/categories")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MobileApi::class.java)
        }
    }
}