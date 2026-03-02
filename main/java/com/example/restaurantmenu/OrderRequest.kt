package com.example.restaurantmenu

data class OrderRequest(
    val items: List<OrderItem>
)

data class OrderItem(
    val name: String,
    val quantity: Int
)