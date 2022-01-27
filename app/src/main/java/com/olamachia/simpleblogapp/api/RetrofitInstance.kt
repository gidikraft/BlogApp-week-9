package com.olamachia.simpleblogapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{
        private var localInstance: Retrofit? = null

        val instance: Retrofit
            get() {
                if (localInstance == null)
                    localInstance = Retrofit.Builder()
                        .baseUrl("https://jsonplaceholder.typicode.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                return localInstance!!
            }

        val api by lazy {
            instance.create(BlogAPI::class.java)
        }
    }
}