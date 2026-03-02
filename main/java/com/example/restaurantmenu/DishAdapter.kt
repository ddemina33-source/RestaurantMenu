package com.example.restaurantmenu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

class DishAdapter(context: Context, private val dishes: List<Dish>) :
    ArrayAdapter<Dish>(context, 0, dishes) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_dish, parent, false)

        val dish = dishes[position]

        val ivDish = view.findViewById<ImageView>(R.id.ivDish)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        val btnAdd = view.findViewById<Button>(R.id.btnAddToCart)

        btnAdd.setOnClickListener {
            CartManager.addToCart(dish)
            Toast.makeText(context, "Добавлено в корзину", Toast.LENGTH_SHORT).show()
        }

        tvName.text = dish.name
        tvPrice.text = "${dish.price} руб"

        // Если картинка из URL (API)
        if (dish.imageUrl != null) {
            Glide.with(context)
                .load(dish.imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(ivDish)
        } else if (dish.imageResId != null) {
            ivDish.setImageResource(dish.imageResId)
        }

        return view
    }
}