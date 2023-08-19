package com.example.retrofitexample.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder private constructor() {

    companion object {
        fun getInstance(): Retrofit {
            val gson: Gson = GsonBuilder().serializeNulls().create()
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            val okHttpClient: OkHttpClient =
                OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
            return Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
        }
    }
}