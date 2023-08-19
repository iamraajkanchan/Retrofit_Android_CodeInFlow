package com.example.retrofitexample.model

import com.google.gson.annotations.SerializedName

class Post(
    val userId: Int,
    val id: Int?,
    val title: String,
    @SerializedName("body") val text: String
) {
    constructor(userIdParam: Int, titleParam: String, bodyParam: String) : this(
        userIdParam,
        null,
        titleParam,
        bodyParam
    )
}
// @SerializedName is used to map with name of the variable of data class and the variable of JSON API you are importing.
// In this scenario, the name of the data class "Post" is "text" but the the name of the same data in JSON APi is body.