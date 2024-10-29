package com.amirmohammad.test2_digi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

data class Category(
    val category: String,
    val products: List<Product>
)

data class ApiResponse(
    val categories: List<Category>
)

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryContainer: LinearLayout
    private var currentImageIndex = 0
    private lateinit var imageChangeHandler: Handler
    private lateinit var imageChangeRunnable: Runnable
    private lateinit var d_slide: ImageView
    private lateinit var categories: List<Category> // لیست دسته‌ها

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // پیدا کردن ویوها
        viewPager = findViewById(R.id.viewPager)
        recyclerView = findViewById(R.id.recyclerView)
        categoryContainer = findViewById(R.id.imageViewContainer)
        d_slide = findViewById(R.id.d_Slide)


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_home // آیکن خانه را به‌عنوان انتخاب‌شده تنظیم کنید

        // سایر تنظیمات
        setupViewPager()
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        Handler(Looper.getMainLooper()).postDelayed({ sendRequest() }, 2500)
        setupAutoImageSlider()
        setupRecyclerViewScrollListener()
        menu()
    }

    private fun setupViewPager() {
        val imageUrls = listOf(
            "https://dkstatics-public.digikala.com/digikala-adservice-banners/6d35e00155f1deca98a5f560c21a9c6f8a5116cb_1730015747.gif?x-oss-process=image",
            "https://dkstatics-public.digikala.com/digikala-adservice-banners/3eedd87cf39e3e62328182c390ceb71f24ae1028_1729673119.jpg?x-oss-process=image/quality,q_95",
            "https://dkstatics-public.digikala.com/digikala-adservice-banners/5cc8977a49c6bf9ebc020d2f58125675bf3d09a8_1729670797.jpg?x-oss-process=image/quality,q_95",

        )

        viewPager.adapter = SliderAdapter(imageUrls)
        viewPager.setPageTransformer { page, position ->
            when {
                position < -1 -> { page.alpha = 0f }
                position <= 1 -> {
                    page.alpha = 1f
                    page.translationX = -position * page.width
                }
                else -> { page.alpha = 0f }
            }
        }
    }

    private fun setupAutoImageSlider() {
        val imageCount = viewPager.adapter?.itemCount ?: 0
        imageChangeHandler = Handler(Looper.getMainLooper())

        imageChangeRunnable = Runnable {
            if (imageCount > 0) {
                currentImageIndex = (currentImageIndex + 1) % imageCount
                viewPager.setCurrentItem(currentImageIndex, true)
            }
            imageChangeHandler.postDelayed(imageChangeRunnable, 2500)
        }

        imageChangeHandler.postDelayed(imageChangeRunnable, 2500)
    }

    private fun setupRecyclerViewScrollListener() {
        val delayHandler = Handler(Looper.getMainLooper())
        var isHidden = false

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0 && !isHidden) {
                    delayHandler.postDelayed({
                        viewPager.visibility = View.GONE
                        categoryContainer.visibility = View.GONE
                    }, 1000)
                    isHidden = true
                } else if (dy < 0 && isHidden) {
                    viewPager.visibility = View.VISIBLE
                    categoryContainer.visibility = View.VISIBLE
                    isHidden = false
                }
            }
        })
    }

    private fun sendRequest() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://10.0.2.2:5000/get-categories") // اطمینان حاصل کنید که این URL درست است
            .build()

        Thread {
            try {
                val response: Response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    val responseBody = response.body?.string()

                    // اکنون از JsonObject استفاده کنید
                    val jsonResponse = Gson().fromJson(responseBody, JsonObject::class.java)

                    // به آرایه categories دسترسی پیدا کنید
                    val categoriesJsonArray = jsonResponse.getAsJsonArray("categories")

                    val products = mutableListOf<Product>()
                    for (i in 0 until categoriesJsonArray.size()) {
                        val categoryObject = categoriesJsonArray.get(i).asJsonObject
                        val productsJsonArray = categoryObject.getAsJsonArray("products")

                        for (j in 0 until productsJsonArray.size()) {
                            val productObject = productsJsonArray.get(j).asJsonObject
                            val title = productObject.get("title").asString
                            val price = productObject.get("price").asDouble
                            val description = productObject.get("description").asString
                            val imageUrl = productObject.get("image_url").asString

                            // ایجاد شیء Product با تمامی پارامترها
                            products.add(Product(title, price, description, imageUrl))
                        }
                    }

                    runOnUiThread {
                        // آداپتر را با تعداد محصولات دریافت‌شده تنظیم کنید
                        recyclerView.adapter = ProductAdapter(products)
                        recyclerView.adapter?.notifyDataSetChanged() // اطمینان از بروزرسانی آداپتر
                    }
                } else {
                    runOnUiThread {
                        // مدیریت خطا، مثلاً نشان دادن پیغام خطا
                        Toast.makeText(this, "خطا در دریافت داده‌ها", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: IOException) {
                Log.e("API Request", "IOException occurred: ${e.message}")
                runOnUiThread {
                    Toast.makeText(this, "خطا در برقراری ارتباط", Toast.LENGTH_SHORT).show()
                }
            } catch (e: JsonSyntaxException) {
                Log.e("API Request", "JsonSyntaxException occurred: ${e.message}")
                runOnUiThread {
                    Toast.makeText(this, "خطای تجزیه JSON", Toast.LENGTH_SHORT).show()
                }
            }

        }.start()
    }





    private fun menu() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // نمای صفحه اصلی را نشان دهید
                    showAllViews()
                    true
                }
                R.id.nav_categories -> {
                    val intent = Intent(this, Categories_Page::class.java)
                    startActivity(intent)
                    hideAllViews()
                    true
                }
                R.id.nav_cart -> {
                    val intent = Intent(this, CartActivity::class.java)
                    startActivity(intent)


                    hideAllViews()
                    true
                }


                R.id.nav_mag -> {
                    hideAllViews()
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

    // متد برای مخفی کردن تمام نمای صفحه
    private fun hideAllViews() {
        // پنهان کردن ویوها
        viewPager.visibility = View.GONE
        categoryContainer.visibility = View.GONE
        recyclerView.visibility = View.GONE
        d_slide.visibility = View.GONE
    }


    // متد برای نمایش مجدد تمام نمای صفحه
    private fun showAllViews() {
        viewPager.visibility = View.VISIBLE
        categoryContainer.visibility = View.VISIBLE
        recyclerView.visibility = View.VISIBLE
        d_slide.visibility = View.VISIBLE
        sendRequest()
    }
}