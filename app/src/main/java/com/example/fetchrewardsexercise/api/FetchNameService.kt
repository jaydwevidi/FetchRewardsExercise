package com.example.fetchrewardsexercise.api

import com.example.fetchrewards.models.NameObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object FetchNameHelper{

    val baseURL = "https://fetch-hiring.s3.amazonaws.com"

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    val instance = getInstance().create(FetchNameService::class.java)

}
interface FetchNameService {


    @GET("/hiring.json")
    suspend fun getNames() : Response<NameObject>
}

