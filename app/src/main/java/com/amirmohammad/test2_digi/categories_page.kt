package com.amirmohammad.test2_digi

import ProductAdapter_category
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class Categories_Page : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter_category

    // متغیر ثابت برای URL
    private val apiUrl = "http://10.0.2.2:5000/get-categories"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_categories_page)

        setupViews()
        fetchCategories() // بارگذاری دسته‌بندی‌ها
    }

    private fun setupViews() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // تنظیم RecyclerView
        recyclerView = findViewById(R.id.recycler_category)
        recyclerView.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter_category(emptyList())
        recyclerView.adapter = productAdapter

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_category)
        bottomNavigationView.selectedItemId = R.id.nav_categories

        menu(bottomNavigationView)
        menu2_Right()
    }

    private fun menu(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.nav_categories -> true
                R.id.nav_cart -> {
                    startActivity(Intent(this, CartActivity::class.java))
                    true
                }
                R.id.nav_mag -> true
                R.id.nav_profile -> {
                    startActivity(Intent(this, Profile_Activity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun menu2_Right() {
        val bottomNavigationView = findViewById<NavigationView>(R.id.navigation_categories)

        bottomNavigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.goods_Digital -> {
                    fetchProducts("کالای دیجیتال")
                    true
                }
                R.id.goods_Stationery -> {
                    fetchProducts("لوازم التحریر")
                    true
                }
                R.id.goods_clothing -> {
                    fetchProducts("پوشاک")
                    true
                }
                R.id.goods_Sports_equipment -> {
                    fetchProducts("لوازم ورزشی")
                    true
                }
                else -> false
            }
        }
    }

    private fun fetchCategories() {
        CoroutineScope(Dispatchers.IO).launch {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(apiUrl) // استفاده از متغیر ثابت
                .build()

            try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val responseData = response.body?.string()
                    responseData?.let {
                        // تبدیل پاسخ به ApiResponse
                        val apiResponseType = object : TypeToken<ApiResponse>() {}.type
                        val apiResponse = Gson().fromJson<ApiResponse>(it, apiResponseType)

                        if (apiResponse.categories.isNotEmpty()) {
                            val firstCategoryProducts = apiResponse.categories[0].products
                            withContext(Dispatchers.Main) {
                                productAdapter.updateProducts(firstCategoryProducts)
                            }
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        showToast("خطا در بارگذاری داده‌ها: ${response.message}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showToast("خطای شبکه: ${e.message}")
                }
                Log.e("Network Error", e.toString())
            }
        }
    }

    private fun fetchProducts(categoryName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(apiUrl) // استفاده از متغیر ثابت
                .build()

            try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val responseData = response.body?.string()
                    responseData?.let {
                        // تبدیل پاسخ به ApiResponse
                        val apiResponseType = object : TypeToken<ApiResponse>() {}.type
                        val apiResponse = Gson().fromJson<ApiResponse>(it, apiResponseType)

                        // پیدا کردن محصولات دسته‌بندی مورد نظر
                        val products = apiResponse.categories.find { it.category == categoryName }?.products ?: emptyList()

                        updateProductRecyclerView(products)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        showToast("خطا در بارگذاری داده‌ها: ${response.message}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showToast("خطای شبکه: ${e.message}")
                }
                Log.e("Network Error", e.toString())
            }
        }
    }

    private suspend fun updateProductRecyclerView(products: List<Product>) {
        withContext(Dispatchers.Main) {
            productAdapter.updateProducts(products)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
