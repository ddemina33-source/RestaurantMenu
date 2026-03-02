package com.example.restaurantmenu

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

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

        // Получаем данные
        val name = intent.getStringExtra("dish_name")
        val price = intent.getIntExtra("dish_price", 0)
        val description = intent.getStringExtra("dish_description")
        val imageUrl = intent.getStringExtra("dish_image_url")

        // Устанавливаем текст
        tvName.text = name
        tvPrice.text = "$price руб"
        tvDescription.text = description

        // Загружаем картинку через Glide
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background) // можно заменить
            .into(ivDish)
    }
}