//package com.amirmohammad.test2_digi
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import okhttp3.Response
//import java.io.IOException
//
//class LoadingPageActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.loading_page)
//
//        // تنظیم padding برای برقراری تعادل با system bars
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        // ارسال درخواست
//        sendRequest()
//    }
//
//    private fun sendRequest() {
//        val client = OkHttpClient()
//        val request = Request.Builder()
//            .url("http://10.0.2.2:5000/get-categories") // URL API خود را وارد کنید
//            .build()
//
//        // اجرای درخواست در یک Thread جدید
//        Thread {
//            try {
//                val response: Response = client.newCall(request).execute()
//
//                // بررسی کد وضعیت پاسخ
//                if (response.isSuccessful) { // 200 OK
//                    // پاسخ موفق است، می‌توانید به Activity بعدی بروید
//                    runOnUiThread {
//                        val intent = Intent(this, MainActivity::class.java) // نام Activity بعدی را وارد کنید
//                        startActivity(intent)
//                        finish() // برای بسته شدن Activity فعلی
//                    }
//                } else {
//                    // مدیریت خطا: اگر کد وضعیت غیر از 200 باشد
//                    runOnUiThread {
//                        Toast.makeText(this, "خطا در دریافت داده‌ها: ${response.code}", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            } catch (e: IOException) {
//                e.printStackTrace()
//                runOnUiThread {
//                    Toast.makeText(this, "خطا در برقراری ارتباط", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }.start()
//    }
//}


//-----------------------------

package com.amirmohammad.test2_digi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class LoadingPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.loading_page)

        // تنظیم padding برای برقراری تعادل با system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ارسال درخواست
        sendRequest()
    }

    private fun sendRequest() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://10.0.2.2:5000/get-categories") // URL API خود را وارد کنید
            .build()

        // اجرای درخواست در یک Thread جدید
        Thread {
            var retry = true // متغیر برای کنترل تلاش مجدد
            while (retry) {
                try {
                    val response: Response = client.newCall(request).execute()

                    // بررسی کد وضعیت پاسخ
                    if (response.isSuccessful) { // 200 OK
                        // پاسخ موفق است، می‌توانید به Activity بعدی بروید
                        runOnUiThread {
                            val intent = Intent(this, MainActivity::class.java) // نام Activity بعدی را وارد کنید
                            startActivity(intent)
                            finish() // برای بسته شدن Activity فعلی
                        }
                        retry = false // چون موفق بودیم، دیگر نیازی به تلاش مجدد نیست
                    } else {
                        // مدیریت خطا: اگر کد وضعیت غیر از 200 باشد
                        runOnUiThread {
                            Toast.makeText(this, "خطا در دریافت داده‌ها: ${response.code}", Toast.LENGTH_SHORT).show()
                        }
                        Thread.sleep(2000) // مکث 2 ثانیه‌ای قبل از تلاش مجدد
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                    runOnUiThread {
                        Toast.makeText(this, "خطا در برقراری ارتباط", Toast.LENGTH_SHORT).show()
                    }
                    Thread.sleep(2000) // مکث 2 ثانیه‌ای در صورت خطا
                }
            }
        }.start()
    }
}
