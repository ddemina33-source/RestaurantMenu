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
    private val dishList = mutableListOf<Dish>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCart = findViewById<Button>(R.id.btnCart)

        btnCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        listViewMenu = findViewById(R.id.listViewMenu)

        // Добавляем блюда
        dishList.addAll(
            listOf(
                Dish(
                    "Цезарь с курицей",
                    450,
                    "Курица, салат, пармезан, соус",
                    imageResId = R.drawable.caesar
                ),
                Dish(
                    "Паста Карбонара",
                    520,
                    "Спагетти, бекон, сливки, сыр",
                    imageResId = R.drawable.carbonara
                ),
                Dish(
                    "Пицца Маргарита",
                    600,
                    "Томаты, моцарелла, базилик",
                    imageResId = R.drawable.pizza
                ),
                Dish(
                    "Том-ям суп",
                    590,
                    "Острый суп с креветками и грибами",
                    imageResId = R.drawable.tomyam
                ),
                Dish(
                    "Чизкейк Нью-Йорк",
                    380,
                    "Классический чизкейк с малиновым соусом",
                    imageResId = R.drawable.cheesecake
                ),
                Dish(
                    "Американо",
                    150,
                    "Эспрессо с горячей водой",
                    imageResId = R.drawable.americano
                ),
                Dish(
                    "Фреш апельсин",
                    280,
                    "Свежевыжатый апельсиновый сок",
                    imageResId = R.drawable.juice
                )
            )
        )

        val adapter = DishAdapter(this, dishList)
        listViewMenu.adapter = adapter

        RetrofitClient.api.getDishes().enqueue(object : Callback<List<DishResponse>> {
            override fun onResponse(
                call: Call<List<DishResponse>>,
                response: Response<List<DishResponse>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { apiDishes ->
                        dishList.clear()
                        apiDishes.forEach {
                            dishList.add(
                                Dish(
                                    it.name,
                                    it.price,
                                    it.description,
                                    imageUrl = it.imageUrl
                                )
                            )
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<List<DishResponse>>, t: Throwable) {
                t.printStackTrace()
            }
        })

        listViewMenu.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedDish = dishList[position]
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("dish_name", selectedDish.name)
                putExtra("dish_price", selectedDish.price)
                putExtra("dish_description", selectedDish.description)
                putExtra("dish_image", selectedDish.imageResId)
            }
            startActivity(intent)
        }
    }
}