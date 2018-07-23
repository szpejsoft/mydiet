package com.szpejsoft.mydiet.views.settings

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.szpejsoft.mydiet.base.BaseViewModel
import com.szpejsoft.mydiet.domain.Settings
import com.szpejsoft.mydiet.utils.SchedulersFacade
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class SettingsViewModel
@Inject
constructor(
        application: Application,
        schedulersFacade: SchedulersFacade,
        private val settingsRepository: ISettingsRepository
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
        settingsRepository.getSettings.subscribeOnCompObserveOnUIBy {
            setAllowedFruitPortions(it.allowedFruitPortions)
            setAllowedVegetablePortions(it.allowedVegetablePortions)
            setAllowedGrainPortions(it.allowedGrainPortions)
            setAllowedDairyPortions(it.allowedDairyPortions)
            setAllowedMeatPortions(it.allowedMeatPortions)
            setAllowedFatPortions(it.allowedFatPortions)
            setIntervalBetweenMeals(it.intervalBetweenMeals)
        }
    }

    override fun setAllowedFruitPortionsObservable(fruitPortions: Observable<Int>) {
        fruitPortions.distinctUntilChanged().subscribeBy { setAllowedFruitPortions(it) }
    }

    private fun setAllowedFruitPortions(portions: Int) {
        allowedFruitPortions = portions
        fruitPortionsData.postValue(portions)
        saveButtonEnabledData.postValue(true)
    }

    override fun setAllowedVegetablePortionsObservable(vegetablePortions: Observable<Int>) {
        vegetablePortions.distinctUntilChanged().subscribeBy { setAllowedVegetablePortions(it) }
    }

    private fun setAllowedVegetablePortions(portions: Int) {
        allowedVegetablePortions = portions
        vegetablePortionsData.postValue(portions)
        saveButtonEnabledData.postValue(true)
    }

    override fun setAllowedGrainPortionsObservable(grainPortions: Observable<Int>) {
        grainPortions.distinctUntilChanged().subscribeBy { setAllowedGrainPortions(it) }
    }

    private fun setAllowedGrainPortions(portions: Int) {
        allowedGrainPortions = portions
        grainPortionsData.postValue(portions)
        saveButtonEnabledData.postValue(true)
    }

    override fun setAllowedDairyPortionsObservable(dairyPortions: Observable<Int>) {
        dairyPortions.distinctUntilChanged().subscribeBy { setAllowedDairyPortions(it) }
    }

    private fun setAllowedDairyPortions(portions: Int) {
        allowedDairyPortions = portions
        dairyPortionsData.postValue(portions)
        saveButtonEnabledData.postValue(true)
    }

    override fun setAllowedMeatPortionsObservable(meatPortions: Observable<Int>) {
        meatPortions.distinctUntilChanged().subscribeBy { setAllowedMeatPortions(it) }
    }

    private fun setAllowedMeatPortions(portions: Int) {
        allowedMeatPortions = portions
        meatPortionsData.postValue(portions)
        saveButtonEnabledData.postValue(true)
    }

    override fun setAllowedFatPortionsObservable(fatPortions: Observable<Int>) {
        fatPortions.distinctUntilChanged().subscribeBy { setAllowedFatPortions(it) }
    }

    private fun setAllowedFatPortions(portions: Int) {
        allowedFatPortions = portions
        fatPortionsData.postValue(portions)
        saveButtonEnabledData.postValue(true)
    }

    override fun setIntervalBetweenMealsObservable(interval: Observable<Int>) {
        interval.distinctUntilChanged().subscribe { setIntervalBetweenMeals(it) }
    }

    private fun setIntervalBetweenMeals(interval: Int) {
        intervalBetweenMeals = interval
        intervalBetweenMealsData.postValue(interval)
        saveButtonEnabledData.postValue(true)
    }

    override fun onSaveBtnClicked(clicked: Observable<Any>) {
        clicked.subscribeBy {
            settingsRepository.saveSettings(Settings(0,
                    allowedFruitPortions = allowedFruitPortions,
                    allowedVegetablePortions = allowedVegetablePortions,
                    allowedGrainPortions = allowedGrainPortions,
                    allowedDairyPortions = allowedDairyPortions,
                    allowedMeatPortions = allowedMeatPortions,
                    allowedFatPortions = allowedFatPortions,
                    intervalBetweenMeals = intervalBetweenMeals
            ))
            saveButtonEnabledData.postValue(false)
        }
    }

}