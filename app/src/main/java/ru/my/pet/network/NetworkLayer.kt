package ru.my.pet.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.my.pet.utils.Constants.Companion.BASE_URL

object NetworkLayer {
    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    val appService: AppService by lazy{
        retrofit.create(AppService::class.java)
    }

    val apiClient = ApiClient(appService)
}