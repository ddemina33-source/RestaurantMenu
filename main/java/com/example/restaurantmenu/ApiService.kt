package com.example.restaurantmenu

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface ApiService {

    @POST("orders")
    fun createOrder(@Body order: OrderRequest): Call<Void>

    @GET("dishes")
    fun getDishes(): Call<List<DishResponse>>
}