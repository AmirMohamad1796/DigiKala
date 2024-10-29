//package com.amirmohammad.test2_digi
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.squareup.picasso.Picasso
//
//data class Product(val title: String, val price: Double, val description: String, val imageUrl: String)
//
//class ProductAdapter(private val productList: List<Product>) :
//    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
//
//    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val titleTextView: TextView = itemView.findViewById(R.id.itemTitleTextView)
//        val priceTextView: TextView = itemView.findViewById(R.id.itemPriceTextView)
//        val descriptionTextView: TextView = itemView.findViewById(R.id.itemDescriptionTextView)
//        val imageView: ImageView = itemView.findViewById(R.id.itemImageView)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
//        return ProductViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
//        val product = productList[position]
//        holder.titleTextView.text = product.title
//        holder.priceTextView.text = "Price: $" + product.price
//        holder.descriptionTextView.text = product.description
//        Picasso.get().load(product.imageUrl).into(holder.imageView)
//    }
//
//    override fun getItemCount(): Int {
//        return productList.size
//    }
//}

//*******************************************
//
//package com.amirmohammad.test2_digi
//
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//
//data class Product(val title: String, val price: Double, val description: String, val imageUrl: String)
//
//class ProductAdapter(private val products: List<Product>) :
//    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
//        return ProductViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
//        val product = products[position]
//        holder.bind(product)
//
//        // کلیک‌لیسنر برای هر آیتم
//        holder.itemView.setOnClickListener {
//            val context = holder.itemView.context
//            val intent = Intent(context, ProductDetailActivity::class.java).apply {
//                putExtra("product_title", product.title)
//                putExtra("product_price", product.price)
//                putExtra("product_description", product.description)
//                putExtra("product_image_url", product.imageUrl)
//            }
//            context.startActivity(intent)
//        }
//    }
//
//    override fun getItemCount(): Int = products.size
//
//    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val title: TextView = itemView.findViewById(R.id.productTitle)
//        private val price: TextView = itemView.findViewById(R.id.productPrice)
//        private val image: ImageView = itemView.findViewById(R.id.productImage)
//
//        fun bind(product: Product) {
//            title.text = product.title
//            price.text = "${product.price} تومان"
//            // لود کردن تصویر با Glide
//            Glide.with(itemView.context).load(product.imageUrl).into(image)
//        }
//    }
//}


//--------------

//package com.amirmohammad.test2_digi
//
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//
//data class Product(val title: String, val price: Double, val description: String, val imageUrl: String)
//
//class ProductAdapter(private val products: List<Product>) :
//    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
//        return ProductViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
//        val product = products[position]
//        holder.bind(product)
//
//        // کلیک‌لیسنر برای هر آیتم
//        holder.itemView.setOnClickListener {
//            val context = holder.itemView.context
//            val intent = Intent(context, ProductDetailActivity::class.java).apply {
//                putExtra("product_title", product.title)
//                putExtra("product_price", product.price)
//                putExtra("product_description", product.description)
//                putExtra("product_image_url", product.imageUrl)
//            }
//            context.startActivity(intent)
//        }
//    }
//
//    override fun getItemCount(): Int = products.size
//
//    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val title: TextView = itemView.findViewById(R.id.productTitle)
//        private val price: TextView = itemView.findViewById(R.id.productPrice)
//        private val image: ImageView = itemView.findViewById(R.id.productImage)
//
//        fun bind(product: Product) {
//            title.text = product.title
//            price.text = "${product.price} تومان"
//            // لود کردن تصویر با Glide
//            Glide.with(itemView.context).load(product.imageUrl).into(image)
//        }
//    }
//}


//---------------------------------
//
//package com.amirmohammad.test2_digi
//
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.engine.DiskCacheStrategy
//
//// مدل محصول
//data class Product(val title: String, val price: Double, val description: String, val image_url: String)
//
//class ProductAdapter(private val products: List<Product>) :
//    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
//
//    // ایجاد ViewHolder برای هر آیتم
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
//        return ProductViewHolder(view)
//    }
//
//    // بستن داده‌ها به ViewHolder
//    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
//        val product = products[position]
//        holder.bind(product)
//
//        // کلیک‌لیسنر برای هر آیتم
//        holder.itemView.setOnClickListener {
//            val context = holder.itemView.context
//            val intent = Intent(context, ProductDetailActivity::class.java).apply {
//                putExtra("product_title", product.title)
//                putExtra("product_price", product.price)
//                putExtra("product_description", product.description)
//                putExtra("product_image_url", product.image_url)
//            }
//            context.startActivity(intent)
//        }
//    }
//
//    // تعداد آیتم‌ها
//    override fun getItemCount(): Int = products.size
//
//    // ViewHolder برای نمایش هر محصول
//    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val title: TextView = itemView.findViewById(R.id.productTitle)
//        private val price: TextView = itemView.findViewById(R.id.productPrice)
//        private val image: ImageView = itemView.findViewById(R.id.productImage)
//
//        // متد برای بستن داده‌ها به ویو
//        fun bind(product: Product) {
//            title.text = product.title
//            price.text = "${product.price} تومان"
//            // بارگذاری تصویر با Glide و کش
//            Glide.with(itemView.context)
//                .load(product.image_url)
//                .diskCacheStrategy(DiskCacheStrategy.ALL) // کش تمام تصاویر
//                .placeholder(R.drawable.placeholder) // تصویر پیش‌فرض در حین بارگذاری
//                .error(R.drawable.error) // تصویر خطا
//                .into(image)
//        }
//    }
//}


//****-----0000-*-*-*-*-*-*-*-*-*--*

//package com.amirmohammad.test2_digi
//
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.engine.DiskCacheStrategy
//import java.text.NumberFormat
//import java.util.Locale
//
//// مدل محصول
//data class Product(val title: String, val price: Double, val description: String, val image_url: String)
//
//class ProductAdapter(private val products: List<Product>) :
//    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
//
//    // ایجاد ViewHolder برای هر آیتم
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
//        return ProductViewHolder(view)
//    }
//
//    // بستن داده‌ها به ViewHolder
//    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
//        val product = products[position]
//        holder.bind(product)
//
//        // کلیک‌لیسنر برای هر آیتم
//        holder.itemView.setOnClickListener {
//            val context = holder.itemView.context
//            val intent = Intent(context, ProductDetailActivity::class.java).apply {
//                putExtra("product_title", product.title)
//                putExtra("product_price", product.price)
//                putExtra("product_description", product.description)
//                putExtra("product_image_url", product.image_url)
//            }
//            context.startActivity(intent)
//        }
//    }
//
//    // تعداد آیتم‌ها
//    override fun getItemCount(): Int = products.size
//
//    // ViewHolder برای نمایش هر محصول
//    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val title: TextView = itemView.findViewById(R.id.productTitle)
//        private val price: TextView = itemView.findViewById(R.id.productPrice)
//        private val image: ImageView = itemView.findViewById(R.id.productImage)
//
//        // متد برای بستن داده‌ها به ویو
//        fun bind(product: Product) {
//            title.text = product.title
//            price.text = formatPrice(product.price) // فرمت کردن قیمت
//            // بارگذاری تصویر با Glide و کش
//            Glide.with(itemView.context)
//                .load(product.image_url)
//                .diskCacheStrategy(DiskCacheStrategy.ALL) // کش تمام تصاویر
//                .placeholder(R.drawable.placeholder) // تصویر پیش‌فرض در حین بارگذاری
//                .error(R.drawable.error) // تصویر خطا
//                .into(image)
//        }
//
//        // متد برای فرمت کردن قیمت
//        private fun formatPrice(price: Double): String {
//            val formattedPrice = NumberFormat.getCurrencyInstance(Locale("fa", "IR")).format(price)
//            return formattedPrice.replace("تومان", " تومان") // اضافه کردن فاصله قبل از تومان
//        }
//    }
//}


//*-*---*-*-*-*-*-**-

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
