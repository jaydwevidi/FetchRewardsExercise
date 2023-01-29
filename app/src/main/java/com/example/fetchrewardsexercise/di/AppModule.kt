package com.example.fetchrewardsexercise.di

import android.app.Application
import android.content.Context
import com.example.fetchrewardsexercise.api.FetchNameService
import com.example.fetchrewardsexercise.repository.NameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.jar.Attributes.Name
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiInstance(): FetchNameService {
        val baseURL = "https://fetch-hiring.s3.amazonaws.com"
        return Retrofit.Builder()
            .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
            .build().create(FetchNameService::class.java)
    }q

    @Provides
    @Singleton
    fun provideRepository(fetchNameService: FetchNameService , appContext: Application) : NameRepository{
        return NameRepository(fetchNameService , appContext )
    }
}