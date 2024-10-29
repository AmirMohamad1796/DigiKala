package com.amirmohammad.test2_digi

data class Product(
    val title: String,
    val price: Double,
    val description: String,
    val imageUrl: String,
    var quantity: Int = 1 // مقدار پیش‌فرض برای quantity
)
