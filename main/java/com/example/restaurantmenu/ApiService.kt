package com.example.restaurantmenu

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("dishes")
    fun getDishes(): Call<List<DishResponse>>
}