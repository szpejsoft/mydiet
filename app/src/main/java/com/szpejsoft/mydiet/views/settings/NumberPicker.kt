package com.szpejsoft.mydiet.views.settings

import android.content.Context
import android.support.annotation.AttrRes
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.jakewharton.rxrelay2.BehaviorRelay
import com.szpejsoft.mydiet.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.number_picker_lay.view.*


class NumberPicker(context: Context,
                   attrs: AttributeSet? = null,
                   @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val valueRelay: BehaviorRelay<Int> = BehaviorRelay.create()
    private var value: Int = 0
    private var minValue = -1
    private var maxValue = 1

    init {
        removeAllViews()
        View.inflate(context, R.layout.number_picker_lay, this)
        plusBtn.setOnClickListener { increment() }
        minusBtn.setOnClickListener { decrement() }

    }

    fun getValueObservable(): Observable<Int> = valueRelay

    fun setRange(min: Int, max: Int) {
        if (min > max) throw IllegalArgumentException("min must me less or equal to max")
        minValue = min
        maxValue = max
        value = clamp(min, max, value)
    }

    fun setValue(value: Int) {
        this.value = clamp(minValue, maxValue, value)
        valueRelay.accept(value)
    }

    private fun decrement() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun increment() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun clamp(min: Int, max: Int, value: Int): Int = when {
        value < min -> min
        value > max -> max
        else -> value
    }


}