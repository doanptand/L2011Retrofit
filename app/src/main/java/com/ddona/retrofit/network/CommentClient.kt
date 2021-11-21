package com.ddona.retrofit.network

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CommentClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.cypress.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    operator fun invoke(): CommentService =
        retrofit.create(CommentService::class.java)

}