package com.geekhaven.alumx.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    // Android Emulator localhost alias
    private const val BASE_URL = "http://10.0.2.2:8080/"

    val api: AlumXApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlumXApi::class.java)
    }
}
