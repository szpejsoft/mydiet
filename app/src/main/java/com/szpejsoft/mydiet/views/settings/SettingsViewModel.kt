package com.szpejsoft.mydiet.views.settings

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.szpejsoft.mydiet.base.BaseViewModel
import com.szpejsoft.mydiet.utils.SchedulersFacade
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class SettingsViewModel
@Inject
constructor(
        application: Application,
        schedulersFacade: SchedulersFacade
) : BaseViewModel(application, schedulersFacade), ISettingsViewModel {
    override val fruitPortionsData = MutableLiveData<Int>()
    override val vegetablePortionsData = MutableLiveData<Int>()
    override val grainPortionsData = MutableLiveData<Int>()
    override val dairyPortionsData = MutableLiveData<Int>()
    override val meatPortionsData = MutableLiveData<Int>()
    override val fatPortionsData = MutableLiveData<Int>()
    override val intervalBetweenMealsData = MutableLiveData<Int>()
    override val saveButtonEnabledData = MutableLiveData<Boolean>()

    private var allowedFruitPortions: Int = 0
    private var allowedVegetablePortions: Int = 0
    private var allowedGrainPortions: Int = 0
    private var allowedDairyPortions: Int = 0
    private var allowedMeatPortions: Int = 0
    private var allowedFatPortions: Int = 0
    private var intervalBetweenMeals: Int = 0

    init {
        saveButtonEnabledData.postValue(false)
    }

    override fun setAllowedFruitPortionsObservable(fruitPortions: Observable<Int>) {
        fruitPortions.subscribeBy { setAllowedFruitPortions(it) }
    }

    private fun setAllowedFruitPortions(portions: Int) {
        allowedFruitPortions = portions
        fruitPortionsData.postValue(allowedDairyPortions)
        saveButtonEnabledData.postValue(true)
    }

    override fun setAllowedVegetablePortionsObservable(vegetablePortions: Observable<Int>) {
        vegetablePortions.subscribeBy { setAllowedVegetablePortions(it) }
    }

    private fun setAllowedVegetablePortions(portions: Int) {
        allowedVegetablePortions = portions
        vegetablePortionsData.postValue(portions)
        saveButtonEnabledData.postValue(true)
    }

    override fun setAllowedGrainPortionsObservable(grainPortions: Observable<Int>) {
        grainPortions.subscribeBy { setAllowedGrainPortions(it) }
    }

    private fun setAllowedGrainPortions(portions: Int) {
        allowedGrainPortions = portions
        grainPortionsData.postValue(portions)
        saveButtonEnabledData.postValue(true)
    }

    override fun setAllowedDairyPortionsObservable(dairyPortions: Observable<Int>) {
        dairyPortions.subscribeBy { setAllowedDairyPortions(it) }
    }

    private fun setAllowedDairyPortions(portions: Int) {
        allowedDairyPortions = portions
        dairyPortionsData.postValue(portions)
        saveButtonEnabledData.postValue(true)
    }

    override fun setAllowedMeatPortionsObservable(meatPortions: Observable<Int>) {
        meatPortions.subscribeBy { setAllowedMeatPortions(it) }
    }

    private fun setAllowedMeatPortions(portions: Int) {
        allowedMeatPortions = portions
        meatPortionsData.postValue(portions)
        saveButtonEnabledData.postValue(true)
    }

    override fun setAllowedFatPortionsObservable(fatPortions: Observable<Int>) {
        fatPortions.subscribeBy { setAllowedFatPortions(it) }
    }

    private fun setAllowedFatPortions(portions: Int) {
        allowedFatPortions = portions
        fatPortionsData.postValue(portions)
        saveButtonEnabledData.postValue(true)
    }

    override fun setIntervalBetweenMealsObservable(interval: Observable<Int>) {
        interval.subscribe { setIntervalBetweenMeals(it) }
    }

    private fun setIntervalBetweenMeals(interval: Int) {
        intervalBetweenMeals = interval
        intervalBetweenMealsData.postValue(interval)
        saveButtonEnabledData.postValue(true)
    }

    override fun onSaveBtnClicked(clicked: Observable<Any>) {
        //write to database
        saveButtonEnabledData.postValue(false)
    }

}