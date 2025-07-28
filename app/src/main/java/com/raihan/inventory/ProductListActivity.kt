package com.raihan.inventory

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raihan.inventory.adapter.ProductAdapter
import com.raihan.inventory.model.CartItem
import com.raihan.inventory.model.Product

class ProductListActivity : AppCompatActivity() {

    private val cartItems = arrayListOf<CartItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewProducts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val products = listOf(
            Product(1, "Kopi", 15000, "https://via.placeholder.com/150"),
            Product(2, "Teh", 12000, "https://via.placeholder.com/150"),
            Product(3, "Susu", 18000, "https://via.placeholder.com/150")
        )

        val productAdapter = ProductAdapter(products) { product ->
            addToCart(product)
        }
        recyclerView.adapter = productAdapter

        findViewById<View>(R.id.btnViewCart).setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            intent.putParcelableArrayListExtra("cart_items", cartItems)
            startActivity(intent)
        }
    }

    private fun addToCart(product: Product) {
        val existing = cartItems.find { it.product.id == product.id }
        if (existing != null) {
            existing.quantity++
        } else {
            cartItems.add(CartItem(product, 1))
        }
    }
}
