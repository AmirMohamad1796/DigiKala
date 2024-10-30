package com.amirmohammad.test2_digi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.Locale

class CartAdapter(
    private val productList: MutableList<Product>,
    private val onDeleteItemClick: (Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.cartProductTitle)
        val price: TextView = itemView.findViewById(R.id.cartProductPrice)
        val quantityInput: EditText = itemView.findViewById(R.id.cartProductQuantity)
        val deleteButton: Button = itemView.findViewById(R.id.delete_itemCart)
        val confirmButton: Button = itemView.findViewById(R.id.confirmQuantityButton)
        val image: ImageView = itemView.findViewById(R.id.cartProductImage)

        init {
            // Listener برای دکمه تایید
            confirmButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val quantity = quantityInput.text.toString().toIntOrNull() ?: 1
                    productList[position].quantity = quantity // به‌روزرسانی تعداد
                    notifyItemChanged(position) // برای به‌روزرسانی قیمت
                    (itemView.context as CartActivity).updateTotalPrice(productList) // بروزرسانی مجموع قیمت
                    CartManager.saveCart(itemView.context, productList) // ذخیره تغییرات
                }
            }

            // Listener برای تغییرات در تعداد
            quantityInput.setOnFocusChangeListener { _, _ ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val quantity = quantityInput.text.toString().toIntOrNull() ?: 1
                    productList[position].quantity = quantity
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = productList[position]
        holder.title.text = product.title

        // قالب‌بندی و نمایش قیمت کالا
        val formattedPrice = formatPrice(product.price)
        holder.price.text = "$formattedPrice "

        // مقداردهی اولیه به EditText
        holder.quantityInput.setText(product.quantity.toString())

        // بارگذاری تصویر با استفاده از Glide
        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.image)

        // مدیریت کلیک روی دکمه حذف
        holder.deleteButton.setOnClickListener {
            onDeleteItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    private fun formatPrice(price: Double): String {
        val formattedPrice = NumberFormat.getNumberInstance(Locale("fa", "IR")).format(price)
        return "$formattedPrice ریال"
    }
}
