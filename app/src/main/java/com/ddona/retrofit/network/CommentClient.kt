package com.ddona.retrofit.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CommentClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.cypress.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    operator fun invoke(): CommentService =
        retrofit.create(CommentService::class.java)

}