package com.szpejsoft.mydiet.viewmodels

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.szpejsoft.mydiet.alarms.AlarmManager
import com.szpejsoft.mydiet.base.BaseViewModel
import com.szpejsoft.mydiet.domain.MealAlarm
import com.szpejsoft.mydiet.domain.Portions
import com.szpejsoft.mydiet.repositories.NourishmentRepository
import com.szpejsoft.mydiet.repositories.SettingsRepository
import com.szpejsoft.mydiet.utils.SchedulersFacade
import io.reactivex.Observable
import javax.inject.Inject

class NourishmentViewModel
@Inject
constructor(
        application: Application,
        schedulersFacade: SchedulersFacade,
        private val settingsRepository: SettingsRepository,
        private val nourishmentRepository: NourishmentRepository,
        private val alarmManager: AlarmManager
) : BaseViewModel(application, schedulersFacade), INourishmentViewModel {
    override val fruitPortionsData = MutableLiveData<Portions>()
    override val vegetablePortionsData = MutableLiveData<Portions>()
    override val grainPortionsData = MutableLiveData<Portions>()
    override val dairyPortionsData = MutableLiveData<Portions>()
    override val meatPortionsData = MutableLiveData<Portions>()
    override val fatPortionsData = MutableLiveData<Portions>()
    override val nextMealIntervalData = MutableLiveData<String>()
    override val nextMealAlarmSet = MutableLiveData<MealAlarm>()

    init {
        fruitPortionsData.postValue(Portions(1, 5))
        vegetablePortionsData.postValue(Portions(0, 5))
        grainPortionsData.postValue(Portions(6, 3))
        dairyPortionsData.postValue(Portions(2, 2))
        meatPortionsData.postValue(Portions(3, 3))
        fatPortionsData.postValue(Portions(0, 0))
        nextMealIntervalData.postValue("3:14")
    }

    override fun setFruitPortionsObservable(observable: Observable<Portions>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setVegetablePortionsObservable(observable: Observable<Portions>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setGrainPortionsObservable(observable: Observable<Portions>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDairyPortionsObservable(observable: Observable<Portions>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setMeatPortionsObservable(observable: Observable<Portions>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setFatPortionsObservable(observable: Observable<Portions>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setNextAlarm() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun saveEatenFruitPortions(portions: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveEatenVegetablePortions(portions: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveEatenGrainPortions(portions: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveEatenDairyPortions(portions: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveEatenMeatPortions(portions: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveEatenFatPortions(portions: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setNextMealAlarm(minutes: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}