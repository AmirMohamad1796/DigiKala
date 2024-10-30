package com.amirmohammad.test2_digi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.Locale

class ProductAdapter_category(private var products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter_category.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.product_title)
        val price: TextView = itemView.findViewById(R.id.product_price)
        val image: ImageView = itemView.findViewById(R.id.product_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_category, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]

        holder.title.text = product.title ?: "عنوان نامشخص"

        // فرمت کردن قیمت
        val formattedPrice = NumberFormat.getNumberInstance(Locale("fa", "IR")).format(product.price)
        holder.price.text = "$formattedPrice ریال"

        val imageUrl = product.imageUrl
        Log.d("ProductAdapter", "Product at position $position has imageUrl: $imageUrl")

        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(holder.itemView.context)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.image)
        } else {
            holder.image.setImageResource(R.drawable.ic_launcher_background)
            Log.e("ProductAdapter", "Image URL is null or empty for product at position $position")
        }
    }

    override fun getItemCount(): Int = products.size

    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }
}
