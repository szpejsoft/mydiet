package com.szpejsoft.mydiet

import android.content.Context
import android.util.TypedValue

fun Int.getPx(context: Context): Int = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics)).toInt()


