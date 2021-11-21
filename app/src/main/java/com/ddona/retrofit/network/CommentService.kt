package com.ddona.retrofit.network

import com.ddona.retrofit.model.Comment
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.*

interface CommentService {

    @GET("comments/")
    fun getAllComments(): Call<List<Comment>>

    @GET("comments/")
    suspend fun getAllCommentsWithCoroutines(): List<Comment>

    @GET("comments/")
    fun getAllCommentsWithRx(): Observable<List<Comment>>

    @GET("comments/{id}")
    @Headers("Content-Type: application/json", "Content-Length: 15402")
    fun getCommentById(@Path("id") postId: String): Call<Comment>

    @POST("posts")
    fun addComment(@Header("Content-Type") contentType: String, @Body comment: Comment)

    //    https://jsonplaceholder.cypress.io/comment?user_id=5&field=id&sort=desc
    @GET("comments")
    suspend fun getCommentsWithQuery(
        @Query("user_id") userId: Int,
        @Query("field") searchField: String,
        @Query("sort") sortType: String
    ): List<Comment>


    @PUT("comment/{id}")
    suspend fun updateComment(@Path("id") id: Int, @Body comment: Comment)

    @PATCH("comment/{id}")
    suspend fun updateComment2(@Path("id") id: Int, @Body comment: Comment)

    @DELETE("comment/{id}")
    suspend fun deleteComment(@Path("id") id: Int)
}