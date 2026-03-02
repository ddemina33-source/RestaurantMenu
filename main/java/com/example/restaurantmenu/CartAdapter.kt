package com.example.restaurantmenu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CartAdapter(
    context: Context,
    private val cartItems: List<CartItem>
) : ArrayAdapter<CartItem>(context, 0, cartItems) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)

        val item = cartItems[position]

        val text1 = view.findViewById<TextView>(android.R.id.text1)
        val text2 = view.findViewById<TextView>(android.R.id.text2)

        text1.text = "${item.dish.name} x${item.quantity}"
        text2.text = "${item.dish.price * item.quantity} руб"

        return view
    }
}