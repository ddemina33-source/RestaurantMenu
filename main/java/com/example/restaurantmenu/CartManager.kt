package com.example.restaurantmenu

object CartManager {

    val cartItems = mutableListOf<CartItem>()

    fun addToCart(dish: Dish) {
        val existingItem = cartItems.find { it.dish.name == dish.name }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            cartItems.add(CartItem(dish))
        }
    }

    fun clearCart() {
        cartItems.clear()
    }

    fun getTotalPrice(): Int {
        return cartItems.sumOf { it.dish.price * it.quantity }
    }
}