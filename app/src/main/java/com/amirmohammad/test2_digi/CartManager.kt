//package com.amirmohammad.test2_digi
//
//import android.content.Context
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//
//object CartManager {
//
//    private const val PREFS_NAME = "cart_prefs"
//    private const val CART_ITEMS_KEY = "cart_items"
//
//    fun saveCart(context: Context, products: List<Product>) {
//        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//        val editor = prefs.edit()
//        val json = Gson().toJson(products)
//        editor.putString(CART_ITEMS_KEY, json)
//        editor.apply()
//    }
//
//    fun loadCart(context: Context): List<Product> {
//        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//        val json = prefs.getString(CART_ITEMS_KEY, null)
//        return if (json != null) {
//            val type = object : TypeToken<List<Product>>() {}.type
//            Gson().fromJson(json, type)
//        } else {
//            emptyList()
//        }
//    }
//}


//*************

package com.amirmohammad.test2_digi

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CartManager {

    private const val PREFS_NAME = "cart_prefs"
    private const val CART_ITEMS_KEY = "cart_items"
    private const val USER_NAME_KEY = "user_name"

    fun saveCart(context: Context, products: List<Product>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val json = Gson().toJson(products)
        editor.putString(CART_ITEMS_KEY, json)
        editor.apply()
    }

    fun loadCart(context: Context): List<Product> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(CART_ITEMS_KEY, null)
        return if (json != null) {
            val type = object : TypeToken<List<Product>>() {}.type
            Gson().fromJson(json, type)
        } else {
            emptyList()
        }
    }

    // ذخیره نام کاربر
    fun saveUserName(context: Context, userName: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(USER_NAME_KEY, userName).apply()
    }

    // حذف نام کاربر
    fun removeUserName(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(USER_NAME_KEY).apply()
    }

    // بارگذاری نام کاربر
    fun loadUserName(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(USER_NAME_KEY, null)
    }
}
