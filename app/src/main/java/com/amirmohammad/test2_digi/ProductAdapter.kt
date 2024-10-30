package com.amirmohammad.test2_digi

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.text.NumberFormat
import java.util.Locale

// مدل محصول


class ProductAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // ایجاد ViewHolder برای هر آیتم
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    // بستن داده‌ها به ViewHolder
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)

        // کلیک‌لیسنر برای هر آیتم
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ProductDetailActivity::class.java).apply {
                putExtra("product_title", product.title)
                putExtra("product_price", product.price)
                putExtra("product_description", product.description)
                putExtra("product_image_url", product.imageUrl)
            }
            context.startActivity(intent)
        }
    }

    // تعداد آیتم‌ها
    override fun getItemCount(): Int = products.size

    // ViewHolder برای نمایش هر محصول
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.productTitle)
        private val price: TextView = itemView.findViewById(R.id.productPrice)
        private val image: ImageView = itemView.findViewById(R.id.productImage)

        // متد برای بستن داده‌ها به ویو
        fun bind(product: Product) {
            title.text = product.title
            price.text = formatPrice(product.price) // فرمت کردن قیمت

            // بارگذاری تصویر با Glide و کش
            Glide.with(itemView.context)
                .load(product.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL) // کش تمام تصاویر
                .placeholder(R.drawable.placeholder) // تصویر پیش‌فرض در حین بارگذاری
                .error(R.drawable.error) // تصویر خطا
                .into(image)
        }

        // متد برای فرمت کردن قیمت
        private fun formatPrice(price: Double): String {
            val formattedPrice = NumberFormat.getCurrencyInstance(Locale("fa", "IR")).format(price)
            return formattedPrice.replace("ریال", " ریال") // اضافه کردن فاصله قبل از تومان
        }
    }
}
