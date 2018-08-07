package com.szpejsoft.mydiet.screens.nourishments

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
import com.jakewharton.rxrelay2.BehaviorRelay
import com.szpejsoft.mydiet.R
import com.szpejsoft.mydiet.domain.Portions
import com.szpejsoft.mydiet.getPx
import kotlinx.android.synthetic.main.portion_counter.view.*

class PortionCounter
constructor(context: Context,
            attrs: AttributeSet? = null,
            @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val surfeitUncheckedDrawable = ContextCompat.getDrawable(context, R.drawable.nourishment_surfeit_unchecked)
    private val surfeitCheckedDrawable = ContextCompat.getDrawable(context, R.drawable.nourishment_surfeit_checked)
    private val allowedUncheckedDrawable = ContextCompat.getDrawable(context, R.drawable.nourishment_allowed_unchecked)
    private val allowedCheckedDrawable = ContextCompat.getDrawable(context, R.drawable.nourishment_allowed_checked)
    private val eatenPortionsObservable: BehaviorRelay<Portions> = BehaviorRelay.create()

    init {
        removeAllViews()
        View.inflate(context, R.layout.portion_counter, this)
    }

    private var eaten: Int = 0
    private var allowed: Int = 0

    fun setPortions(eaten: Int, allowed: Int) {
        this.eaten = eaten
        this.allowed = allowed
        portionsLay.removeAllViews()
        addAllowed(eaten, allowed)
        if (eaten > allowed) {
            addSurfeit(eaten, allowed)
        }
        addNotCheckedSurfeitView()
        eatenPortionsObservable.accept(Portions(eaten, allowed))
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
            val drawable = if (i <= eaten) allowedCheckedDrawable else allowedUncheckedDrawable
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

    }

    private fun createView(): AppCompatImageView {
        val view = AppCompatImageView(context)
        view.layoutParams = LinearLayout.LayoutParams(CHECK_VIEW_SIZE_DP.getPx(context), CHECK_VIEW_SIZE_DP.getPx(context))
        return view
    }

    //index is starting from 1
    private fun checkClicked(index: Int) {
        when (index) {
            eaten -> handleLastCheckedClicked()
            eaten + 1 -> handleFirstUncheckedClicked()
            else -> {
            } //dunuthin
        }
        eatenPortionsObservable.accept(Portions(eaten, allowed))
    }

    private fun handleLastCheckedClicked() {
        val clickedView = portionsLay[getLayoutIndex(eaten)] as AppCompatImageView
        when {
            eaten > allowed ->
                portionsLay.removeView(clickedView)
            eaten == allowed -> {
                clickedView.setImageDrawable(allowedUncheckedDrawable)
                portionsLay.removeViewAt(getLayoutIndex(eaten) + 1)
            }
            eaten <= allowed -> clickedView.setImageDrawable(allowedUncheckedDrawable)
        }
        eaten--
    }

    private fun handleFirstUncheckedClicked() {
        val clickedIndex = eaten + 1
        val clickedView = portionsLay[getLayoutIndex(clickedIndex)] as AppCompatImageView
        when {
            clickedIndex < allowed -> clickedView.setImageDrawable(allowedCheckedDrawable)
            clickedIndex == allowed -> {
                clickedView.setImageDrawable(allowedCheckedDrawable)
                addNotCheckedSurfeitView()
            }
            clickedIndex > allowed -> {
                clickedView.setImageDrawable(surfeitCheckedDrawable)
                addNotCheckedSurfeitView()
            }
        }
        eaten++
    }

    private fun getLayoutIndex(index: Int) = index - 1

    companion object {
        const val CHECK_VIEW_SIZE_DP = 48
    }
}
