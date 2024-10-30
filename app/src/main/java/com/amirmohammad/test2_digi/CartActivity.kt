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
    }
}
