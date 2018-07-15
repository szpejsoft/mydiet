package com.szpejsoft.mydiet.utils

fun calculateBMI(weight: Float, height: Float) = weight / (height * height)

fun getCategory(weight: Float, height: Float): BMICategory {
    val bmi = calculateBMI(weight, height)
    return BMICategory.values().first { it.min <= bmi && it.max > bmi }
}

enum class BMICategory(val min: Float, val max: Float) {
    SEVERELY_UNDERWEIGHT(0.0f, 16.0f),
    UNDERWEIGHT(16.0f, 18.5f),
    NORMAL(18.5f, 25.0f),
    OVERWEIGHT(25.0f, 30.0f),
    OBESE(30.0f, 35.0f),
    SEVERELY_OBESE(35.0f, Float.MAX_VALUE)
}