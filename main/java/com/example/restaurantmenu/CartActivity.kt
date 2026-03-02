package com.example.restaurantmenu

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val listView = findViewById<ListView>(R.id.listViewCart)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val btnOrder = findViewById<Button>(R.id.btnOrder)

        val adapter = CartAdapter(this, CartManager.cartItems)
        listView.adapter = adapter

        tvTotal.text = "Итого: ${CartManager.getTotalPrice()} руб"

        btnOrder.setOnClickListener {
            sendOrderToServer()
        }
    }

    private fun sendOrderToServer() {

        val orderRequest = OrderRequest(
            CartManager.cartItems.map {
                OrderItem(it.dish.name, it.quantity)
            }
        )

        RetrofitClient.api.createOrder(orderRequest)
            .enqueue(object : retrofit2.Callback<Void> {
                override fun onResponse(
                    call: retrofit2.Call<Void>,
                    response: retrofit2.Response<Void>
                ) {
                    CartManager.clearCart()
                    finish()
                }

                override fun onFailure(call: retrofit2.Call<Void>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}