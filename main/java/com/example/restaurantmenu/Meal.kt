package com.example.restaurantmenu

data class MealResponse(
    val meals: List<Meal>?
)

data class Meal(
    val strMeal: String,
    val strCategory: String,
    val strInstructions: String,
    val strMealThumb: String
)