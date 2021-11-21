package com.ddona.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Comment(
    @Expose
    @SerializedName("body")
    var body: String,
    @Expose
    @SerializedName("email")
    var email: String,
    @Expose
    @SerializedName("id")
    var id: Int,
    @Expose
    @SerializedName("name")
    var name: String,
    @Expose
    @SerializedName("postId")
    var postId: Int
)