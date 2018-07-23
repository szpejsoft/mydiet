package com.szpejsoft.mydiet.viewmodels

import android.arch.lifecycle.LiveData
import io.reactivex.Completable
import io.reactivex.Observable

interface ISettingsViewModel {
    val fruitPortionsData: LiveData<Int>
    val vegetablePortionsData: LiveData<Int>
    val grainPortionsData: LiveData<Int>
    val dairyPortionsData: LiveData<Int>
    val meatPortionsData: LiveData<Int>
    val fatPortionsData: LiveData<Int>
    val intervalBetweenMealsData: LiveData<Int>
    val saveButtonEnabledData: LiveData<Boolean>

    fun setAllowedFruitPortionsObservable(fruitPortions: Observable<Int>)
    fun setAllowedVegetablePortionsObservable(vegetablePortions: Observable<Int>)
    fun setAllowedGrainPortionsObservable(grainPortions: Observable<Int>)
    fun setAllowedDairyPortionsObservable(dairyPortions: Observable<Int>)
    fun setAllowedMeatPortionsObservable(meatPortions: Observable<Int>)
    fun setAllowedFatPortionsObservable(fatPortions: Observable<Int>)
    fun setIntervalBetweenMealsObservable(interval: Observable<Int>)
    fun onSaveBtnClicked(clicked: Observable<Any>)

}