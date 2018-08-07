package com.szpejsoft.mydiet.screens.nourishments

import android.arch.lifecycle.LiveData
import com.szpejsoft.mydiet.domain.MealAlarm
import com.szpejsoft.mydiet.domain.Portions
import io.reactivex.Observable
import org.joda.time.LocalDate


interface INourishmentViewModel {
    val fruitPortionsData: LiveData<Portions>
    val vegetablePortionsData: LiveData<Portions>
    val grainPortionsData: LiveData<Portions>
    val dairyPortionsData: LiveData<Portions>
    val meatPortionsData: LiveData<Portions>
    val fatPortionsData: LiveData<Portions>
    val nextMealIntervalData: LiveData<String>
    val nextMealAlarmSet: LiveData<MealAlarm> //TODO pokazać alert wyjeżdżający z góry z godziną następnego posiłku (Alerter)
    val showSetNextAlarmButton: LiveData<Boolean>
    val dateData: LiveData<String>

    fun setDate(date: LocalDate)
    fun setFruitPortionsObservable(observable: Observable<Int>)
    fun setVegetablePortionsObservable(observable: Observable<Int>)
    fun setGrainPortionsObservable(observable: Observable<Int>)
    fun setDairyPortionsObservable(observable: Observable<Int>)
    fun setMeatPortionsObservable(observable: Observable<Int>)
    fun setFatPortionsObservable(observable: Observable<Int>)
    fun setNextAlarm()

}