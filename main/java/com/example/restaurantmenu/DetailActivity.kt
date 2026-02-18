package com.example.restaurantmenu

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    private lateinit var tvName: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvDescription: TextView
    private lateinit var ivDish: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tvName = findViewById(R.id.tvDishName)
        tvPrice = findViewById(R.id.tvDishPrice)
        tvDescription = findViewById(R.id.tvDishDescription)
        ivDish = findViewById(R.id.ivDishDetail)

        val imageRes = intent.getIntExtra("dish_image", 0)
        ivDish.setImageResource(imageRes)

        // Получаем данные из Intent
        val name = intent.getStringExtra("dish_name")
        val price = intent.getStringExtra("dish_price")
        val description = intent.getStringExtra("dish_description")

        tvName.text = name
        tvPrice.text = price
        tvDescription.text = description
    }
}