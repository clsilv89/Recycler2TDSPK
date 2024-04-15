package com.caiosilva.recycler2tdspk

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIServices {

    @GET("/posts")
    fun getPosts(): Call<List<Post>>

    @GET("/photos")
    fun getPhotos(): Call<List<Album>>

    @POST("/posts")
    fun postPost(@Body post: PostPost): Call<Post>
}