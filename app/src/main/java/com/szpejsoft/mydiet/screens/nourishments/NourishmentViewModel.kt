package com.szpejsoft.mydiet.screens.nourishments

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.szpejsoft.mydiet.alarms.AlarmManager
import com.szpejsoft.mydiet.base.BaseViewModel
import com.szpejsoft.mydiet.debugToLogcat
import com.szpejsoft.mydiet.domain.*
import com.szpejsoft.mydiet.repositories.NourishmentRepository
import com.szpejsoft.mydiet.repositories.SettingsRepository
import com.szpejsoft.mydiet.utils.SchedulersFacade
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import org.joda.time.LocalDate
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
    override val dateData = MutableLiveData<String>()
    override val fruitPortionsData = MutableLiveData<Portions>()
    override val vegetablePortionsData = MutableLiveData<Portions>()
    override val grainPortionsData = MutableLiveData<Portions>()
    override val dairyPortionsData = MutableLiveData<Portions>()
    override val meatPortionsData = MutableLiveData<Portions>()
    override val fatPortionsData = MutableLiveData<Portions>()
    override val nextMealIntervalData = MutableLiveData<String>()
    override val nextMealAlarmSet = MutableLiveData<MealAlarm>()
    override val showSetNextAlarmButton = MutableLiveData<Boolean>()

    private var fruitsConsumed: Int = 0
    private var vegetablesConsumed: Int = 0
    private var grainConsumed: Int = 0
    private var dairyConsumed: Int = 0
    private var meatConsumed: Int = 0
    private var fatConsumed: Int = 0
    private var date: LocalDate = LocalDate()

    override fun setDate(date: LocalDate) {
        Log.d("ptsz", "vm setDate $date")
        dateData.postValue(date.toString(DATE_FORMAT))
        Observable.combineLatest(settingsRepository.getSettingsForDate(date).toObservable().debugToLogcat("ptsz nvm settings"),
                nourishmentRepository.getNourishmentForDate(date).debugToLogcat("ptsz nvm nourishments"),
                BiFunction<Settings, Nourishment, Pair<Settings, Nourishment>> { settings, nourishment ->
                    settings to nourishment
                })
                .debugToLogcat("vm setDate")
                .subscribeOnCompObserveOnUIBy {
                    updateInternalData(it.second)
                    sendData(it.first, it.second)
                }
    }


    override fun setFruitPortionsObservable(observable: Observable<Int>) {
        observable.subscribeOnCompObserveOnCompBy {
            fruitsConsumed = it
            nourishmentRepository.saveNourishment(createNourishment())
        }
    }


    override fun setVegetablePortionsObservable(observable: Observable<Int>) {
        observable.subscribeOnCompObserveOnCompBy {
            vegetablesConsumed = it
            nourishmentRepository.saveNourishment(createNourishment())
        }
    }

    override fun setGrainPortionsObservable(observable: Observable<Int>) {
        observable.subscribeOnCompObserveOnCompBy {
            grainConsumed = it
            nourishmentRepository.saveNourishment(createNourishment())
        }
    }

    override fun setDairyPortionsObservable(observable: Observable<Int>) {
        observable.subscribeOnCompObserveOnCompBy {
            dairyConsumed = it
            nourishmentRepository.saveNourishment(createNourishment())
        }
    }

    override fun setMeatPortionsObservable(observable: Observable<Int>) {
        observable.subscribeOnCompObserveOnCompBy {
            meatConsumed = it
            nourishmentRepository.saveNourishment(createNourishment())
        }
    }

    override fun setFatPortionsObservable(observable: Observable<Int>) {
        observable.subscribeOnCompObserveOnCompBy {
            fatConsumed = it
            nourishmentRepository.saveNourishment(createNourishment())
        }
    }

    override fun setNextAlarm() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun createNourishment(): Nourishment = Nourishment(fruitsConsumed = fruitsConsumed,
            vegetablesConsumed = vegetablesConsumed,
            grainConsumed = grainConsumed,
            dairyConsumed = dairyConsumed,
            meatConsumed = meatConsumed,
            fatConsumed = fatConsumed,
            date = date)


    private fun updateInternalData(nourishment: Nourishment) {
        Log.d("ptsz", "updateInternalData")
        fruitsConsumed = nourishment.fruitsConsumed
        vegetablesConsumed = nourishment.vegetablesConsumed
        grainConsumed = nourishment.grainConsumed
        dairyConsumed = nourishment.dairyConsumed
        meatConsumed = nourishment.meatConsumed
        fatConsumed = nourishment.fatConsumed
    }

    private fun sendData(settings: Settings, nourishment: Nourishment) {
        Log.d("ptsz", "sendData")
        fruitPortionsData.postValue(Portions(nourishment.fruitsConsumed, settings.fruitPortionsAllowed))
        vegetablePortionsData.postValue(Portions(nourishment.vegetablesConsumed, settings.vegetablePortionsAllowed))
        grainPortionsData.postValue(Portions(nourishment.grainConsumed, settings.grainPortionsAllowed))
        dairyPortionsData.postValue(Portions(nourishment.dairyConsumed, settings.dairyPortionsAllowed))
        meatPortionsData.postValue(Portions(nourishment.meatConsumed, settings.meatPortionsAllowed))
        fatPortionsData.postValue(Portions(nourishment.fatConsumed, settings.fatPortionsAllowed))
    }

}
