package com.molchanov.repository.remote.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RickAndMortyRequestIml{

    private val baseUrl = "https://rickandmortyapi.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(createOkHttpClient(RMInterceptor()))
        .build()

    fun getCharactersRetrofit(): CharactersApi {
        return retrofit.create(CharactersApi::class.java)
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder().let {
            it.addInterceptor(interceptor)
            it.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return httpClient.build()
    }

    //Перехватчик для отлавливания колбэков о результате загрузки
    class RMInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }
}