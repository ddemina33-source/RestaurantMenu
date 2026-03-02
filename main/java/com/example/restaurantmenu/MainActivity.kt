package com.example.restaurantmenu

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var listViewMenu: ListView
    private lateinit var adapter: DishAdapter
    private val dishList = mutableListOf<Dish>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCart = findViewById<Button>(R.id.btnCart)

        btnCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        listViewMenu = findViewById(R.id.listViewMenu)

        adapter = DishAdapter(this, dishList)
        listViewMenu.adapter = adapter

        loadMeals()

        listViewMenu.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->

                val selectedDish = dishList[position]

                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("dish_name", selectedDish.name)
                    putExtra("dish_price", selectedDish.price)
                    putExtra("dish_description", selectedDish.description)
                    putExtra("dish_image_url", selectedDish.imageUrl)
                }

                startActivity(intent)
            }
    }

    private fun loadMeals() {

        RetrofitClient.api.searchMeals("")
            .enqueue(object : Callback<MealResponse> {

                override fun onResponse(
                    call: Call<MealResponse>,
                    response: Response<MealResponse>
                ) {

                    val meals = response.body()?.meals

                    if (meals != null) {

                        dishList.clear()

                        meals.forEach {
                            dishList.add(
                                Dish(
                                    name = it.strMeal,
                                    price = (200..600).random(),
                                    description = it.strInstructions,
                                    imageUrl = it.strMealThumb
                                )
                            )
                        }

                        adapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}