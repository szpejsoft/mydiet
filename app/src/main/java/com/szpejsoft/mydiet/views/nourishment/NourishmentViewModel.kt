package com.szpejsoft.mydiet.views.nourishment

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.szpejsoft.mydiet.base.BaseViewModel
import com.szpejsoft.mydiet.utils.SchedulersFacade
import javax.inject.Inject


class NourishmentViewModel
@Inject
constructor(
        application: Application,
        schedulersFacade: SchedulersFacade
) : BaseViewModel(application, schedulersFacade), INourishmentViewModel {

    override val fruitsEatenAllowedData = MutableLiveData<Pair<Int, Int>>()
    override val vegetablesEatenAllowedData = MutableLiveData<Pair<Int, Int>>()
    override val grainEatenAllowedData = MutableLiveData<Pair<Int, Int>>()
    override val dairyEatenAllowedData = MutableLiveData<Pair<Int, Int>>()
    override val meatEatenAllowedData = MutableLiveData<Pair<Int, Int>>()
    override val fatEatenAllowedData = MutableLiveData<Pair<Int, Int>>()
    override val nextMealWaitingTime = MutableLiveData<String>()

    init {
        fruitsEatenAllowedData.postValue(Pair(3, 2))
        vegetablesEatenAllowedData.postValue(Pair(4, 4))
        grainEatenAllowedData.postValue(Pair(4, 0))
        dairyEatenAllowedData.postValue(Pair(4, 1))
        meatEatenAllowedData.postValue(Pair(3, 1))
        fatEatenAllowedData.postValue(Pair(0, 0))
        nextMealWaitingTime.postValue("3:14")
    }

    override fun onFruitsEatenChanged(eaten: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onVegetablesEatenChanged(eaten: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onGrainEatenChanged(eaten: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDairyEatenChanged(eaten: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMeatEatenChanged(eaten: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFatEatenChanged(eaten: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNextMealAlertSet() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}