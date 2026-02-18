package com.example.restaurantmenu

data class Dish(
    val name: String,
    val price: Int,
    val description: String,
    val imageUrl: String? = null,
    val imageResId: Int? = null
)

