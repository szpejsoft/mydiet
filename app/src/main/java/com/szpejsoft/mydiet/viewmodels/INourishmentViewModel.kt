package com.szpejsoft.mydiet.viewmodels

import android.arch.lifecycle.LiveData
import com.szpejsoft.mydiet.domain.MealAlarm
import com.szpejsoft.mydiet.domain.Portions
import io.reactivex.Observable

interface INourishmentViewModel {
    val fruitPortionsData: LiveData<Portions>
    val vegetablePortionsData: LiveData<Portions>
    val grainPortionsData: LiveData<Portions>
    val dairyPortionsData: LiveData<Portions>
    val meatPortionsData: LiveData<Portions>
    val fatPortionsData: LiveData<Portions>
    val nextMealIntervalData: LiveData<String>
    val nextMealAlarmSet: LiveData<MealAlarm> //TODO pokazać alert wyjeżdżający z góry z godziną następnego posiłku (Alerter)

    fun setFruitPortionsObservable(observable: Observable<Portions>)
    fun setVegetablePortionsObservable(observable: Observable<Portions>)
    fun setGrainPortionsObservable(observable: Observable<Portions>)
    fun setDairyPortionsObservable(observable: Observable<Portions>)
    fun setMeatPortionsObservable(observable: Observable<Portions>)
    fun setFatPortionsObservable(observable: Observable<Portions>)
    fun setNextAlarm()

    fun saveEatenFruitPortions(portions: Int)
    fun saveEatenVegetablePortions(portions: Int)
    fun saveEatenGrainPortions(portions: Int)
    fun saveEatenDairyPortions(portions: Int)
    fun saveEatenMeatPortions(portions: Int)
    fun saveEatenFatPortions(portions: Int)
    fun setNextMealAlarm(minutes: Int)

}