package com.szpejsoft.mydiet

import android.content.Context
import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View

fun Int.getPx(context: Context): Int = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics)).toInt()
fun Int.formatInterval(): String {
    val hours = "${this / 60}"
    val minutes = "${this % 60}".padStart(2, '0')
    return "$hours:$minutes"
}

fun <T: View> RecyclerView.ViewHolder.bind(@IdRes id: Int): Lazy<T> = lazy { itemView.findViewById<T>(id) }