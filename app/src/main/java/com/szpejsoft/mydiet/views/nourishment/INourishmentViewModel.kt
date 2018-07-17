package com.szpejsoft.mydiet.views.nourishment

import android.arch.lifecycle.LiveData

interface INourishmentViewModel {
    val fruitsEatenAllowedData: LiveData<Pair<Int, Int>>
    val vegetablesEatenAllowedData: LiveData<Pair<Int, Int>>
    val grainEatenAllowedData: LiveData<Pair<Int, Int>>
    val dairyEatenAllowedData: LiveData<Pair<Int, Int>>
    val meatEatenAllowedData: LiveData<Pair<Int, Int>>
    val fatEatenAllowedData: LiveData<Pair<Int, Int>>
    val nextMealWaitingTime: LiveData<String>

    fun onFruitsEatenChanged(eaten: Int)
    fun onVegetablesEatenChanged(eaten: Int)
    fun onGrainEatenChanged(eaten: Int)
    fun onDairyEatenChanged(eaten: Int)
    fun onMeatEatenChanged(eaten: Int)
    fun onFatEatenChanged(eaten: Int)
    fun onNextMealAlertSet()

}