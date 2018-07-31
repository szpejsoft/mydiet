package com.szpejsoft.mydiet.repositories

import com.szpejsoft.mydiet.domain.Portions
import io.reactivex.Observable
import org.joda.time.LocalDate

interface NourishmentRepository {
    val fruitPortionsData: Observable<Portions>
    val vegetablePortionsData: Observable<Portions>
    val grainPortionsData: Observable<Portions>
    val dairyPortionsData: Observable<Portions>
    val meatPortionsData: Observable<Portions>
    val fatPortionsData: Observable<Portions>
    val nextMealIntervalData: Observable<Int>

    fun setDate(date: LocalDate)

    fun saveEatenFruitPortions(portions: Int)
    fun saveEatenVegetablePortions(portions: Int)
    fun saveEatenGrainPortions(portions: Int)
    fun saveEatenDairyPortions(portions: Int)
    fun saveEatenMeatPortions(portions: Int)
    fun saveEatenFatPortions(portions: Int)
}