package com.example.fetchrewardsexercise.api

import com.example.fetchrewards.models.NameObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface FetchNameService {
    @GET("/hiring.json")
    suspend fun getNames() : Response<NameObject>
}

