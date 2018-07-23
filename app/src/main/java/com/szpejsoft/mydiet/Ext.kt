package com.szpejsoft.mydiet

import android.content.Context
import android.util.TypedValue

fun Int.getPx(context: Context): Int = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics)).toInt()
fun Int.formatInterval(): String {
    val hours = "${this / 60}"
    val minutes = "${this % 60}".padStart(2, '0')
    return "$hours:$minutes"
}

