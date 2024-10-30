package com.amirmohammad.test2_digi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class Profile_Activity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var logoutButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var userNameTextView: TextView
    private lateinit var loginTitle: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        emailEditText = findViewById(R.id.input_Email)
        nameEditText = findViewById(R.id.input_Name)
        submitButton = findViewById(R.id.submit_Button)
        logoutButton = findViewById(R.id.logout) // دکمه خروج
        progressBar = findViewById(R.id.progressBar_Login)
        userNameTextView = findViewById(R.id.userNameTextView)
        loginTitle = findViewById(R.id.loginTitle)

        submitButton.setOnClickListener {
            handleLogin()
        }

        logoutButton.setOnClickListener {
            handleLogout()
        }

        // بررسی وضعیت ورود کاربر هنگام ورود به برنامه
        checkLoginStatus()

        setupBottomNavigation()
    }

    // بررسی وضعیت ورود کاربر
    private fun checkLoginStatus() {
        val userName = CartManager.loadUserName(this)
        if (userName != null) {
            // نمایش پیام خوش‌آمدگویی و مخفی کردن فرم لاگین
            userNameTextView.text = "خوش آمدید $userName!"
            userNameTextView.visibility = View.VISIBLE
            logoutButton.visibility = View.VISIBLE

            emailEditText.visibility = View.GONE
            nameEditText.visibility = View.GONE
            submitButton.visibility = View.GONE
            loginTitle.text = "شما ورود کرده اید!"
        } else {
            // نمایش فرم لاگین در صورتی که کاربر لاگین نکرده باشد
            userNameTextView.visibility = View.GONE
            emailEditText.visibility = View.VISIBLE
            nameEditText.visibility = View.VISIBLE
            submitButton.visibility = View.VISIBLE
            logoutButton.visibility = View.GONE
            loginTitle.text = "لطفا ورود نمایید!"
        }
    }

    private fun handleLogin() {
        val email = emailEditText.text.toString()
        val name = nameEditText.text.toString()

        if (email.isEmpty() || name.isEmpty()) {
            return
        }

        progressBar.visibility = View.VISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            progressBar.visibility = View.GONE
            userNameTextView.text = "خوش آمدید $name!"
            userNameTextView.visibility = View.VISIBLE
            logoutButton.visibility = View.VISIBLE

            CartManager.saveUserName(this, name) // ذخیره نام کاربر

            emailEditText.text.clear()
            nameEditText.text.clear()

            emailEditText.visibility = View.GONE
            nameEditText.visibility = View.GONE
            submitButton.visibility = View.GONE
            loginTitle.text = "شما وارد شده اید"
        }, 2000)
    }

    // متد برای مدیریت خروج از حساب
    private fun handleLogout() {
        CartManager.removeUserName(this) // حذف نام کاربر

        // بازنشانی یا انتقال به صفحه ورود
        userNameTextView.visibility = View.GONE
        loginTitle.text = "از حساب کاربری خارج شدید!"
        emailEditText.visibility = View.VISIBLE
        nameEditText.visibility = View.VISIBLE
        submitButton.visibility = View.VISIBLE
        logoutButton.visibility = View.GONE
    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
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
                    val intent = Intent(this, CartActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_mag -> {
                    true
                }
                R.id.nav_profile -> {
                    true
                }
                else -> false
            }
        }
    }
}
