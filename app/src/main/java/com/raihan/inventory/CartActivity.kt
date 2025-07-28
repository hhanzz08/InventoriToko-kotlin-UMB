package com.raihan.inventory

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raihan.inventory.adapter.CartAdapter
import com.raihan.inventory.model.CartItem

class CartActivity : AppCompatActivity() {

    private lateinit var rvCart: RecyclerView
    private lateinit var tvTotalPrice: TextView
    private lateinit var btnCheckout: Button

    private lateinit var cartAdapter: CartAdapter
    private val cartItems = mutableListOf<CartItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        rvCart = findViewById(R.id.rvCart)
        tvTotalPrice = findViewById(R.id.tvTotalPrice)
        btnCheckout = findViewById(R.id.btnCheckout)

        // ✅ Ambil data dari Intent!
        val itemsFromIntent = intent.getParcelableArrayListExtra<CartItem>("cart_items")
        if (itemsFromIntent != null) {
            cartItems.addAll(itemsFromIntent)
        }

        rvCart.layoutManager = LinearLayoutManager(this)
        cartAdapter = CartAdapter(
            cartItems,
            onQuantityChanged = {
                updateTotalPrice()
            },
            onRemoveItem = {
                updateTotalPrice()
            }
        )
        rvCart.adapter = cartAdapter

        updateTotalPrice()

        btnCheckout.setOnClickListener {
            Toast.makeText(this, "Pembayaran Sukses!", Toast.LENGTH_SHORT).show()
            cartItems.clear()
            cartAdapter.notifyDataSetChanged()
            updateTotalPrice()
        }
    }

    private fun updateTotalPrice() {
        val total = cartItems.sumOf { it.product.price * it.quantity }
        tvTotalPrice.text = "Total: Rp $total"
    }
}
