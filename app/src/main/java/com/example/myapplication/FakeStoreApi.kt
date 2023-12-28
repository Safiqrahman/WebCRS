package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface FakeStoreApi {
    @GET("products")
    fun getPosts(): Call<List<PostEntity>>
}


