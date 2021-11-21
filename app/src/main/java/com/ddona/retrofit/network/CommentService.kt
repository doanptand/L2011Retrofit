package com.ddona.retrofit.network

import com.ddona.retrofit.model.Comment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentService {

    @GET("comments/")
    fun getAllComments(): Call<List<Comment>>

    @GET("comments/{id}")
    fun getCommentById(@Path("id") postId:String): Call<Comment>
}