package com.example.retrofitexample

import retrofit2.Call
import retrofit2.http.*

interface JSONPlaceholderAPI
{
    //    Calling this URL https://jsonplaceholder.typicode.com/posts to get posts.
    //    This part (https://jsonplaceholder.typicode.com/) of the URL can be declared as baseURL
    @GET("posts")
    fun getPosts() : Call<List<Post>>

    //    https://jsonplaceholder.typicode.com/comments?postId=1
    @GET("comments")
    fun getCommentsWithPostId(@Query("postId") postId : Int) : Call<List<Comment>>

    //    https://jsonplaceholder.typicode.com/posts/1/comments
    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId : Int) : Call<List<Comment>>

    //    https://jsonplaceholder.typicode.com/comments?postId=1&_sort=id&_order=asc
    @GET("comments")
    fun getSortedCommentsWithPostId(@Query("postId") postId : Int? , @Query("_sort") sort : String? , @Query("_order") order : String?) : Call<List<Comment>>

    //    https://jsonplaceholder.typicode.com/comments?postId=1&postId=3&postId=5&_sort=id&_order=null
    @GET("comments")
    fun getSortedCommentsWithPostIds(@Query("postId") postIds : List<Int>? , @Query("_sort") sort : String? , @Query("_order") order : String?) : Call<List<Comment>>

    //    https://jsonplaceholder.typicode.com/comments?_order=asc&postId=1&_sort=id
    @GET("comments")
    fun getSortedCommentsWithParameters(@QueryMap parameters : Map<String , String>) : Call<List<Comment>>

    //    https://jsonplaceholder.typicode.com/posts/1/comments
    @GET
    fun getCommentsWithURL(@Url url : String) : Call<List<Comment>>

    //    https://jsonplaceholder.typicode.com/posts
    @POST("posts")
    fun createPost(@Body post : Post) : Call<Post>

    //    Please note you can Polymorphism too by using the same name of different methods but with different types of arguments.
    @FormUrlEncoded
    @POST("posts")
    fun createPostWithField(@Field("userId") userId : Int , @Field("title") title : String , @Field("body") text : String) : Call<Post>

    @FormUrlEncoded
    @POST("posts")
    fun createPostWithMap(@FieldMap fields : Map<String , String>) : Call<Post>

    @Headers("Static-Header: 123")
    @PUT("posts/{id}")
    fun putPost(@Path("id") id : Int , @Body post : Post) : Call<Post>

    @Headers("Static-Header: 123")
    @PATCH("posts/{id}")
    fun patchPost(@Path("id") id : Int , @Body post : Post) : Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id : Int) : Call<Post>

}