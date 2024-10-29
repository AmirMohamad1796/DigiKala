////
////import android.view.LayoutInflater
////import android.view.View
////import android.view.ViewGroup
////import android.widget.ImageView
////import android.widget.TextView
////import androidx.recyclerview.widget.RecyclerView
////import com.amirmohammad.test2_digi.Product
////import com.amirmohammad.test2_digi.R
////import com.bumptech.glide.Glide
////
////class ProductAdapter_category(private var products: List<Product>) : RecyclerView.Adapter<ProductAdapter_category.ProductViewHolder>() {
////
////    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
////        val title: TextView = itemView.findViewById(R.id.product_title)
////        val price: TextView = itemView.findViewById(R.id.product_price)
////        val image: ImageView = itemView.findViewById(R.id.product_image)
////    }
////
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
////        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_category, parent, false)
////        return ProductViewHolder(view)
////    }
////
////    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
////        val product = products[position]
////        holder.title.text = product.title
////        holder.price.text = "${product.price} تومان"
////        Glide.with(holder.itemView.context).load(product.imageUrl).into(holder.image)
////    }
////
////    override fun getItemCount(): Int = products.size
////
////    fun updateProducts(newProducts: List<Product>) {
////        products = newProducts
////        notifyDataSetChanged()
////    }
////}
//
//
/////-------------------------
//
//package com.amirmohammad.test2_digi
//
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//
//class ProductAdapter_category(private var products: List<Product>) : RecyclerView.Adapter<ProductAdapter_category.ProductViewHolder>() {
//
//    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val title: TextView = itemView.findViewById(R.id.product_title)
//        val price: TextView = itemView.findViewById(R.id.product_price)
////        val description: TextView = itemView.findViewById(R.id.product_description)
//        val image: ImageView = itemView.findViewById(R.id.product_image)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_category, parent, false)
//        return ProductViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
////        val product = products[position]
////        holder.title.text = product.title
////        holder.price.text = "${product.price} ریال"  // نمایش قیمت با واحد تومان
//////        holder.description.text = product.description
//
//        // بارگذاری تصویر با Glide
////        Glide.with(holder.itemView.context)
////            .load(product.imageUrl)
////            .into(holder.image)
//        val product = products[position]
//
//        holder.title.text = product.title
//        holder.price.text = "${product.price} ریال"
//
//        if (product.imageUrl.isEmpty()) {
//            Log.e("ProductAdapter", "Image URL is empty for product at position $position")
//        } else {
//            Glide.with(holder.itemView.context)
//                .load(product.imageUrl)
//                .placeholder(R.drawable.ic_launcher_background) // تصویر پیش‌فرض
//                .error(R.drawable.error) // در صورت بروز خطا
//                .into(holder.image)
//        }
//
//
//
//    }
//
//    override fun getItemCount(): Int = products.size
//
//    // متد جدید برای به‌روزرسانی لیست محصولات
//    fun updateProducts(newProducts: List<Product>) {
//        products = newProducts
//        notifyDataSetChanged()
//    }
//}


//----------------------------------------
//
//package com.amirmohammad.test2_digi
//
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//
//class ProductAdapter_category(private var products: List<Product>) :
//    RecyclerView.Adapter<ProductAdapter_category.ProductViewHolder>() {
//
//    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val title: TextView = itemView.findViewById(R.id.product_title)
//        val price: TextView = itemView.findViewById(R.id.product_price)
//        val image: ImageView = itemView.findViewById(R.id.product_image)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_product_category, parent, false)
//        return ProductViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
//        val product = products[position]
//
//        // بررسی مقدار null برای title و تنظیم مقدار پیش‌فرض اگر null بود
//        holder.title.text = product.title ?: "عنوان نامشخص"
//        holder.price.text = "${product.price} ریال"
//
//        // بارگذاری تصویر با Glide و بررسی مقدار null برای imageUrl
//        val imageUrl = product.imageUrl
//        if (!imageUrl.isNullOrEmpty()) {
//            // بارگذاری تصویر اگر imageUrl معتبر باشد
//            Glide.with(holder.itemView.context)
//                .load(imageUrl)
//                .placeholder(R.drawable.placeholder) // تصویر پیش‌فرض
//                .error(R.drawable.error) // تصویر در صورت بروز خطا
//                .into(holder.image)
//        } else {
//            // تنظیم تصویر پیش‌فرض اگر imageUrl خالی یا null بود
//            holder.image.setImageResource(R.drawable.ic_launcher_background)
//            Log.e("ProductAdapter", "Image URL is null or empty for product at position $position")
//        }
//    }
//
//    override fun getItemCount(): Int = products.size
//
//    // متد جدید برای به‌روزرسانی لیست محصولات
//    fun updateProducts(newProducts: List<Product>) {
//        products = newProducts
//        notifyDataSetChanged()
//    }
//}


//-------------------

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amirmohammad.test2_digi.Product
import com.amirmohammad.test2_digi.R
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
