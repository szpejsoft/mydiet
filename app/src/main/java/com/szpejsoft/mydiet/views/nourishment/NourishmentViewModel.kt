package com.szpejsoft.mydiet.views.nourishment

import android.arch.lifecycle.MutableLiveData

class NourishmentViewModel : INourishmentViewModel {
    override val fruitsEatenAllowedData = MutableLiveData<Pair<Int, Int>>()
    override val vegetablesEatenAllowedData = MutableLiveData<Pair<Int, Int>>()
    override val grainEatenAllowedData = MutableLiveData<Pair<Int, Int>>()
    override val dairyEatenAllowedData = MutableLiveData<Pair<Int, Int>>()
    override val meatEatenAllowedData = MutableLiveData<Pair<Int, Int>>()
    override val fatEatenAllowedData = MutableLiveData<Pair<Int, Int>>()
    override val nextMealWaitingTime = MutableLiveData<Int>()

    init {
        fruitsEatenAllowedData.postValue(Pair(5, 2))
        vegetablesEatenAllowedData.postValue(Pair(4, 4))
        grainEatenAllowedData.postValue(Pair(4, 0))
        dairyEatenAllowedData.postValue(Pair(4, 1))
        meatEatenAllowedData.postValue(Pair(3, 1))
        fatEatenAllowedData.postValue(Pair(0, 0))
    }

}