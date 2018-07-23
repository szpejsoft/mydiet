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
    val nextMealAlarmSet: LiveData<MealAlarm> //TODO pokazać alert wyjeżdżający z góry (Alerter)

    fun setFruitPortionsObservable(observable: Observable<Portions>)
    fun setVegetablePortionsObservable(observable: Observable<Portions>)
    fun setGrainPortionsObservable(observable: Observable<Portions>)
    fun setDairyPortionsObservable(observable: Observable<Portions>)
    fun setMeatPortionsObservable(observable: Observable<Portions>)
    fun setFatPortionsObservable(observable: Observable<Portions>)
    fun setNextAlarm()


}