//package com.amirmohammad.test2_digi
//
//import android.os.Bundle
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import com.bumptech.glide.Glide
//
//class ProductDetailActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_product_detail)
//
//        // پیدا کردن ویوها
//        val productImage: ImageView = findViewById(R.id.productImage)
//        val productTitle: TextView = findViewById(R.id.productTitle)
//        val productPrice: TextView = findViewById(R.id.productPrice)
//        val productDescription: TextView = findViewById(R.id.productDescription)
//        val btnBack: ImageView = findViewById(R.id.btnBack)
//        val btnAddToCart: Button = findViewById(R.id.btnAddToCart)
//
//        // دریافت اطلاعات محصول از Intent
//        val title = intent.getStringExtra("product_title")
//        val price = intent.getDoubleExtra("product_price", 0.0)
//        val description = intent.getStringExtra("product_description")
//        val imageUrl = intent.getStringExtra("product_image_url")
//
//        // نمایش اطلاعات محصول
//        productTitle.text = title
//        productPrice.text = "$price تومان"
//        productDescription.text = description
//
//        // لود کردن تصویر محصول با استفاده از Glide
//        Glide.with(this).load(imageUrl).into(productImage)
//
//        // تنظیم عملکرد دکمه برگشت
//        btnBack.setOnClickListener {
//            finish() // بستن اکتیویتی و بازگشت به صفحه اصلی
//        }
//
//        // عملکرد دکمه "افزودن به سبد خرید"
//        btnAddToCart.setOnClickListener {
//            // اینجا می‌توانید سبد خرید را مدیریت کنید
//        }
//    }
//}
//----------------------------------------

//package com.amirmohammad.test2_digi
//
//import android.os.Bundle
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.bumptech.glide.Glide
//
//class ProductDetailActivity : AppCompatActivity() {
//
//    private lateinit var productImage: ImageView
//    private lateinit var productTitle: TextView
//    private lateinit var productPrice: TextView
//    private lateinit var productDescription: TextView
//    private lateinit var backButton: Button
//    private lateinit var addToCart: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_product_detail)
//
//        productImage = findViewById(R.id.productImage)
//        productTitle = findViewById(R.id.productTitle)
//        productPrice = findViewById(R.id.productPrice)
//        productDescription = findViewById(R.id.productDescription)
//        backButton = findViewById(R.id.backButton)
//        addToCart = findViewById(R.id.addToCartbtn)
//
//        // دریافت داده‌ها از Intent
//        val title = intent.getStringExtra("product_title")
//        val price = intent.getDoubleExtra("product_price", 0.0)
//        val description = intent.getStringExtra("product_description")
//        val imageUrl = intent.getStringExtra("product_image_url")
//
//        // تنظیم داده‌ها
//        productTitle.text = title
//        productPrice.text = "${price} تومان"
//        productDescription.text = description
//        Glide.with(this).load(imageUrl).into(productImage)
//
//        // دکمه بازگشت
//        backButton.setOnClickListener {
//            finish() // برگشت به صفحه قبلی
//        }
//        addToCart.setOnClickListener {
//            Toast.makeText(this,"CLICKED", Toast.LENGTH_SHORT).show()
//        }
//    }
//}
//----------------------------------------
//package com.amirmohammad.test2_digi
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.bumptech.glide.Glide
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//
//class ProductDetailActivity : AppCompatActivity() {
//
//    private lateinit var productImage: ImageView
//    private lateinit var productTitle: TextView
//    private lateinit var productPrice: TextView
//    private lateinit var productDescription: TextView
//    private lateinit var backButton: Button
//    private lateinit var addToCart: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_product_detail) // اطمینان حاصل کنید که نام فایل XML صحیح است
//
//        // مقداردهی ویوها
//        productImage = findViewById(R.id.productImage)
//        productTitle = findViewById(R.id.productTitle)
//        productPrice = findViewById(R.id.productPrice)
//        productDescription = findViewById(R.id.productDescription)
//        backButton = findViewById(R.id.backButton)
//        addToCart = findViewById(R.id.addToCartbtn)
//
//        // دریافت داده‌ها از Intent
//        val title = intent.getStringExtra("product_title") ?: ""
//        val price = intent.getDoubleExtra("product_price", 0.0)
//        val description = intent.getStringExtra("product_description") ?: ""
//        val imageUrl = intent.getStringExtra("product_image_url") ?: ""
//
//        // تنظیم داده‌ها
//        productTitle.text = title
//        productPrice.text = "${price} تومان"
//        productDescription.text = description
//        Glide.with(this).load(imageUrl).into(productImage)
//
//
////----------------------
//
//        // تابع ذخیره محصول به سبد خرید
//        fun saveToCart(context: Context, product: Product) {
//            val sharedPreferences = context.getSharedPreferences("cart", Context.MODE_PRIVATE)
//            val editor = sharedPreferences.edit()
//            val gson = Gson()
//
//            // بارگذاری محصولات موجود
//            val cartJson = sharedPreferences.getString("cart_items", null)
//            val productList: MutableList<Product> = if (cartJson != null) {
//                gson.fromJson(cartJson, object : TypeToken<MutableList<Product>>() {}.type)
//            } else {
//                mutableListOf()
//            }
//
//            // اضافه کردن محصول جدید
//            productList.add(product)
//
//            // ذخیره مجدد محصولات
//            val newCartJson = gson.toJson(productList)
//            editor.putString("cart_items", newCartJson)
//            editor.apply()
//        }
//
//        // تابع بارگذاری سبد خرید
//        fun loadCart(context: Context): List<Product> {
//            val sharedPreferences = context.getSharedPreferences("cart", Context.MODE_PRIVATE)
//            val gson = Gson()
//            val cartJson = sharedPreferences.getString("cart_items", null)
//
//            return if (cartJson != null) {
//                gson.fromJson(cartJson, object : TypeToken<List<Product>>() {}.type)
//            } else {
//                emptyList()
//            }
//        }
//
////----------------------
//        // دکمه بازگشت
//        backButton.setOnClickListener {
//            finish() // برگشت به صفحه قبلی
//        }
//
//        // دکمه افزودن به سبد خرید
//        addToCart.setOnClickListener {
//            val intent = Intent(this, CartActivity::class.java).apply {
//                    putExtra("cart_title", title)
//                    putExtra("cart_price", price)
//                    putExtra("cart_description", description)
//                    putExtra("cart_image_url", imageUrl)
//
//            }
//            val product = Product(title , price , description , imageUrl)
//            saveToCart(this, product)
//            Toast.makeText(this, "Product added to cart", Toast.LENGTH_SHORT).show()
//            startActivity(intent)
//            Toast.makeText(this, "محصول به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show()
//        }
//
//    }
//}
//-----------------------کد بالا صحیح است

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
