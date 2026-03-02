package com.example.restaurantmenu

data class CartItem(
    val dish: Dish,
    var quantity: Int = 1
)