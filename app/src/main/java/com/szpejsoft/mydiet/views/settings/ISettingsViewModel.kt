package com.szpejsoft.mydiet.views.settings

import io.reactivex.Completable
import io.reactivex.Observable

interface ISettingsViewModel {
    var fruitPortions: Observable<Int>
    var vegetablesPortions: Observable<Int>
    var grainPortions: Observable<Int>
    var dairyPortions: Observable<Int>
    var meatPortions: Observable<Int>
    var fatPortions: Observable<Int>
    var intervalBetweenMeals: Observable<Int>

    fun setFruitPortionsValue(fruitPortions: Observable<Int>)
    fun setVegetablesPortionsValue(vegetablesPortions: Observable<Int>)
    fun setGrainPortionsValue(grainPortions: Observable<Int>)
    fun setDairyPortionsValue(dairyPortions: Observable<Int>)
    fun setMeatPortionsValue(meatPortions: Observable<Int>)
    fun setFatPortionsValue(fatPortions: Observable<Int>)
    fun setintervalBetweenMealsValue(interval: Observable<Int>)
    fun onSaveBtnClicked(clicked: Completable)

}