package com.example.miniapp.dao

import com.example.miniapp.model.Api
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers

interface MiniAppIndicator {


    @Headers("Context:application/json")
    @GET("api")
    fun indicator(@Body api: List<Api>): Call<List<Api>>
}