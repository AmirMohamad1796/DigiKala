//package com.amirmohammad.test2_digi
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//
//class CartActivity : AppCompatActivity() {
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var cartAdapter: CartAdapter
//
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.cart_layout)
//        val cart_title = intent.getStringExtra("cart_title") ?: ""
//        val cart_price = intent.getDoubleExtra("cart_price", 0.0)
//        val cart_description = intent.getStringExtra("cart_description") ?: ""
//        val cart_imageUrl = intent.getStringExtra("cart_image_url") ?: ""
//
//        var product_list= listOf(
//            Product(cart_title,cart_price,cart_description,cart_imageUrl)
//        )
//
//        var recycle_cart =findViewById<RecyclerView>(R.id.recycle_cart)
//        recyclerView = findViewById(R.id.recycle_cart)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//
//
//        cartAdapter = CartAdapter(product_list)
//        recycle_cart.adapter = cartAdapter
//
//
//
////        recyclerView = findViewById(R.id.recyclerViewCart)
////        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // دریافت محصولات از CartManager
////        val cartProducts = CartManager.getProducts() // فرض بر این است که این متد محصولات را برمی‌گرداند
////        cartAdapter = CartAdapter(cartProducts)
//
////        recyclerView.adapter = cartAdapter
//
//
//    }
//}


//--------------*-**-*-*-*-*-*-*-
//package com.amirmohammad.test2_digi
//
//import CartManager
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//
//class CartActivity : AppCompatActivity() {
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var cartAdapter: CartAdapter
//    private lateinit var totalPriceTextView: TextView
//    private var productList: MutableList<Product> = mutableListOf()
//
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.cart_layout)
//
//        // بارگذاری اقلام سبد خرید
//        productList = CartManager.loadCart(this).toMutableList()
//
//        // اضافه کردن محصول جدید از Intent
//        val cartTitle = intent.getStringExtra("cart_title") ?: ""
//        val cartPrice = intent.getDoubleExtra("cart_price", 0.0)
//        val cartDescription = intent.getStringExtra("cart_description") ?: ""
//        val cartImageUrl = intent.getStringExtra("cart_image_url") ?: ""
//
//        if (cartTitle.isNotEmpty()) {
//            val newProduct = Product(cartTitle, cartPrice, cartDescription, cartImageUrl)
//            productList.add(newProduct) // اضافه کردن محصول جدید
//            CartManager.saveCart(this, productList) // ذخیره سبد خرید
//        }
//
//        recyclerView = findViewById(R.id.recycle_cart)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // تنظیم آداپتر
//        cartAdapter = CartAdapter(productList, ::onDeleteItemClick)
//        recyclerView.adapter = cartAdapter
//
//        // محاسبه و نمایش مجموع قیمت
//        totalPriceTextView = findViewById<TextView>(R.id.totalPriceTextView)
//        calculateTotalPrice()
//
//    }
//
//    // متد برای محاسبه مجموع قیمت اقلام
//    private fun calculateTotalPrice() {
//        val totalPrice = productList.sumOf { it.price } // محاسبه مجموع قیمت‌ها
//        totalPriceTextView.text = "Total: $totalPrice" // نمایش در TextView
//    }
//
//    // متد برای حذف آیتم از سبد خرید
//    private fun onDeleteItemClick(position: Int) {
//        productList.removeAt(position) // حذف آیتم از لیست
//        CartManager.saveCart(this, productList) // ذخیره مجدد سبد خرید
//        cartAdapter.notifyItemRemoved(position) // بروزرسانی RecyclerView
//        calculateTotalPrice() // بروزرسانی مجموع قیمت
//    }
//}
//-----------------------------------------کد بالا صحیح است

package com.amirmohammad.test2_digi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.NumberFormat
import java.util.Locale

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var totalPriceTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_layout)

        // مقداردهی اولیه RecyclerView و TextView
        recyclerView = findViewById(R.id.recycle_cart)
        recyclerView.layoutManager = LinearLayoutManager(this)
        totalPriceTextView = findViewById(R.id.totalPriceTextView)

        // بارگذاری محصولات از SharedPreferences
        val productList = CartManager.loadCart(this).toMutableList()

        // تنظیم آداپتر
        cartAdapter = CartAdapter(productList) { position ->
            productList.removeAt(position)
            cartAdapter.notifyItemRemoved(position)
            updateTotalPrice(productList)
            CartManager.saveCart(this, productList)
        }
        recyclerView.adapter = cartAdapter

        // محاسبه و نمایش مجموع قیمت
        updateTotalPrice(productList)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_cart)
        bottomNavigationView.selectedItemId = R.id.nav_cart

        menu()


    }

    // تابع برای به‌روزرسانی مجموع قیمت
    fun updateTotalPrice(productList: List<Product>) {
        val totalPrice = calculateTotalPrice(productList)
        val formattedPrice = formatPrice(totalPrice)
        totalPriceTextView.text = "قیمت نهایی : $formattedPrice "
    }

    // تابع برای محاسبه مجموع قیمت‌ها
    private fun calculateTotalPrice(productList: List<Product>): Double {
        return productList.sumOf { it.price * it.quantity }
    }

    // تابع برای قالب‌بندی قیمت به صورت خوانا
    private fun formatPrice(price: Double): String {
        val formattedPrice = NumberFormat.getNumberInstance(Locale("fa", "IR")).format(price)
        return "$formattedPrice ریال"
    }

    private fun menu() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_cart)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_categories -> {
                    val intent = Intent(this, Categories_Page::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_cart -> {
                    true
                }


                R.id.nav_mag -> {
                    true
                }
                R.id.nav_profile -> {
                    val intent_Profile = Intent(this , Profile_Activity::class.java)
                    startActivity(intent_Profile)
                    hideAllViews()
                    true
                }

                else -> false
            }
        }
    }
    private fun hideAllViews() {
        // پنهان کردن ویوها
//        viewPager.visibility = View.GONE
//        categoryContainer.visibility = View.GONE
        recyclerView.visibility = View.GONE
//        titleTextView.visibility = View.GONE

        // گرفتن FragmentManager و لیست همه Fragmentهای موجود
        val fragmentManager = supportFragmentManager // برای Activity
        val fragments = fragmentManager.fragments

        // پنهان کردن یا حذف Fragmentها
        val transaction = fragmentManager.beginTransaction()
        for (fragment in fragments) {
            if (fragment != null && fragment.isVisible) {
                transaction.hide(fragment) // برای پنهان کردن Fragment
                // یا می‌توانید از remove() استفاده کنید اگر می‌خواهید کاملاً حذف شوند
                // transaction.remove(fragment)
            }
        }
        transaction.commit()
    }
}
