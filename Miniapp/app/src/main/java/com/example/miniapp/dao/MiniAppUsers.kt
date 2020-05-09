package com.example.miniapp.dao

import com.example.miniapp.model.User
import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface MiniAppUsers {

    @Headers("Context:application/json")
    @POST("authenticate")
    fun login(@Query("username") mail: String, @Query("password") password: String): Call<User>
}