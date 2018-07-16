package com.szpejsoft.mydiet.views.nourishment

import android.widget.FrameLayout
import android.content.Context
import android.support.annotation.AttrRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.core.widget.toast
import com.szpejsoft.mydiet.R
import com.szpejsoft.mydiet.getPx
import kotlinx.android.synthetic.main.portion_counter.view.*

class PortionCounter constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : FrameLayout(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val surfeitUncheckedDrawable = ContextCompat.getDrawable(context, R.drawable.surfeit_unchecked)
    private val surfeitCheckedDrawable = ContextCompat.getDrawable(context, R.drawable.surfeit_checked)
    private val allowedUncheckedDrawable = ContextCompat.getDrawable(context, R.drawable.allowed_unchecked)
    private val allowedCheckedDrawable = ContextCompat.getDrawable(context, R.drawable.allowed_checked)

    init {
        removeAllViews()
        View.inflate(context, R.layout.portion_counter, this)
    }

    interface OnCountChangedListener {
        fun onCountChanged(eaten: Int, allowed: Int)
    }

    val onCountChangedListeners = mutableSetOf<OnCountChangedListener>()

    var eaten: Int = 0
    var allowed: Int = 0

    fun addOnCountChangeListener(listener: OnCountChangedListener) {
        onCountChangedListeners.add(listener)
    }

    fun removeOnCountChangedListener(listener: OnCountChangedListener) {
        onCountChangedListeners.remove(listener)
    }

    fun setPortions(eaten: Int, allowed: Int) {
        this.eaten = eaten
        this.allowed = allowed
        portionsLay.removeAllViews()
        addAllowed(eaten, allowed)
        if (eaten > allowed) {
            addSurfeit(eaten, allowed)
        } else {
            addNotCheckedSurfeitView()
        }
    }

    private fun addNotCheckedSurfeitView() {
        val view = createView()
        portionsLay.addView(view)
        view.setOnClickListener { checkClicked(findIndex(view)) }
        view.setImageDrawable(surfeitUncheckedDrawable)
        view.scaleType = ImageView.ScaleType.CENTER
    }

    private fun addAllowed(eaten: Int, allowed: Int) {
        for (i in 1..allowed) {
            val view = createView()
            val drawable = if (i < eaten) allowedCheckedDrawable else allowedUncheckedDrawable
            view.setImageDrawable(drawable)
            view.scaleType = ImageView.ScaleType.CENTER
            portionsLay.addView(view)
            view.setOnClickListener { checkClicked(findIndex(view)) }
        }
    }

    private fun findIndex(view: View): Int {
        for (i in 0 until portionsLay.childCount)
            if (portionsLay[i] == view) return i + 1
        return -1
    }

    private fun addSurfeit(eaten: Int, allowed: Int) {
        for (i in allowed + 1..eaten) {
            val view = createView()
            portionsLay.addView(view)
            view.setOnClickListener { checkClicked(findIndex(view)) }
            view.setImageDrawable(surfeitCheckedDrawable)
            view.scaleType = ImageView.ScaleType.CENTER
        }
        addNotCheckedSurfeitView()
    }

    private fun createView(): AppCompatImageView {
        val view = AppCompatImageView(context)
        view.layoutParams = LinearLayout.LayoutParams(CHECK_VIEW_SIZE_DP.getPx(context), CHECK_VIEW_SIZE_DP.getPx(context))
        return view
    }

    //index is starting from 1
    private fun checkClicked(index: Int) {
        when {
            index == eaten && eaten > allowed -> {
                portionsLay.removeViewAt(getLayoutIndex(eaten))
                eaten--
            }
            index == eaten && eaten <= allowed -> {
                (portionsLay[getLayoutIndex(index)] as AppCompatImageView)
                        .setImageDrawable(allowedUncheckedDrawable)
                if (eaten == allowed) portionsLay.removeViewAt(getLayoutIndex(eaten) + 1)
                eaten--
            }
            index == eaten + 1 && eaten + 1 <= allowed -> {
                (portionsLay[getLayoutIndex(index)] as AppCompatImageView)
                        .setImageDrawable(allowedCheckedDrawable)
                if (eaten + 1 == allowed) addNotCheckedSurfeitView()
                eaten++

            }
            index == eaten + 1 && eaten + 1 > allowed -> {
                (portionsLay[getLayoutIndex(index)] as AppCompatImageView)
                        .setImageDrawable(surfeitCheckedDrawable)
                addNotCheckedSurfeitView()
                eaten++
            }
        }
        onCountChangedListeners.forEach { it.onCountChanged(eaten, allowed) }
    }




    private fun getLayoutIndex(index: Int) = index - 1

    companion object {
        const val CHECK_VIEW_SIZE_DP = 48
    }
}
