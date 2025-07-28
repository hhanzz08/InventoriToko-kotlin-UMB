package com.raihan.inventory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.raihan.inventory.R
import com.raihan.inventory.model.CartItem

class CartAdapter(
    private val cartItems: MutableList<CartItem>,
    private val onQuantityChanged: () -> Unit,
    private val onRemoveItem: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProduct: ImageView = view.findViewById(R.id.ivCartProduct)
        val tvName: TextView = view.findViewById(R.id.tvCartProductName)
        val tvPrice: TextView = view.findViewById(R.id.tvCartProductPrice)
        val tvQty: TextView = view.findViewById(R.id.tvCartQuantity)
        val btnPlus: Button = view.findViewById(R.id.btnIncreaseQty)
        val btnMinus: Button = view.findViewById(R.id.btnDecreaseQty)
        val btnRemove: Button = view.findViewById(R.id.btnRemoveItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun getItemCount() = cartItems.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]

        holder.tvName.text = cartItem.product.name
        holder.tvPrice.text = "Rp ${cartItem.product.price}"
        holder.tvQty.text = cartItem.quantity.toString()

        // ✅ Pakai dummy kalau kosong
        val safeImageUrl = cartItem.product.imageUrl.ifBlank { "https://cdn.pixabay.com/photo/2017/07/14/07/44/strawberries-2502961_1280.jpg" }
        holder.ivProduct.load(safeImageUrl)

        holder.btnPlus.setOnClickListener {
            cartItem.quantity++
            holder.tvQty.text = cartItem.quantity.toString()
            onQuantityChanged()
        }

        holder.btnMinus.setOnClickListener {
            if (cartItem.quantity > 1) {
                cartItem.quantity--
                holder.tvQty.text = cartItem.quantity.toString()
                onQuantityChanged()
            }
        }

        holder.btnRemove.setOnClickListener {
            cartItems.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartItems.size)
            onRemoveItem()
        }
    }
}
