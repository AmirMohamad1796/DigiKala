package com.amirmohammad.test2_digi

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.Locale

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var productImage: ImageView
    private lateinit var productTitle: TextView
    private lateinit var productPrice: TextView
    private lateinit var productDescription: TextView
    private lateinit var backButton: Button
    private lateinit var addToCart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        // مقداردهی ویوها
        productImage = findViewById(R.id.productImage)
        productTitle = findViewById(R.id.productTitle)
        productPrice = findViewById(R.id.productPrice)
        productDescription = findViewById(R.id.productDescription)
        backButton = findViewById(R.id.backButton)
        addToCart = findViewById(R.id.addToCartbtn)

        // دریافت داده‌ها از Intent
        val title = intent.getStringExtra("product_title") ?: ""
        val price = intent.getDoubleExtra("product_price", 0.0)
        val description = intent.getStringExtra("product_description") ?: ""
        val imageUrl = intent.getStringExtra("product_image_url") ?: ""

        Log.d("ProductDetail", "Title: $title, Price: $price, Description: $description, Image URL: $imageUrl")

        // تنظیم داده‌ها
        productTitle.text = title
        productPrice.text = formatPrice(price)
        productDescription.text = description
        Glide.with(this)
            .load(imageUrl.ifEmpty { R.drawable.ic_launcher_background }) // تصویر پیش‌فرض
            .into(productImage)

        // دکمه بازگشت
        backButton.setOnClickListener {
            finish()
        }

        // دکمه افزودن به سبد خرید
        addToCart.setOnClickListener {
            val product = Product(title, price, description, imageUrl)
            val currentCart = CartManager.loadCart(this).toMutableList()
            currentCart.add(product)
            CartManager.saveCart(this, currentCart)
            Toast.makeText(this, "محصول به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show()
        }
    }

    // تابع برای نمایش قیمت با فرمت مناسب
    private fun formatPrice(price: Double): String {
        val formattedPrice = NumberFormat.getNumberInstance(Locale("fa", "IR")).format(price)
        return "$formattedPrice  ریال"}
}
