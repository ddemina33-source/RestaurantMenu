package com.example.restaurantmenu

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.POST
import retrofit2.http.Body

interface ApiService {

    @GET("search.php")
    fun searchMeals(
        @Query("s") query: String
    ): Call<MealResponse>

    @POST("orders")
    fun createOrder(@Body order: OrderRequest): Call<Void>

    @GET("dishes")
    fun getDishes(): Call<List<DishResponse>>
}