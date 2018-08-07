package com.szpejsoft.mydiet.screens.nourishments

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.szpejsoft.mydiet.base.BaseViewModel
import com.szpejsoft.mydiet.domain.Nourishment
import com.szpejsoft.mydiet.repositories.NourishmentRepository
import com.szpejsoft.mydiet.utils.SchedulersFacade
import org.joda.time.LocalDate
import javax.inject.Inject

class NourishmentListViewModel
@Inject
constructor(application: Application,
            schedulersFacade: SchedulersFacade,
            private val nourishmentRepository: NourishmentRepository
) : BaseViewModel(application, schedulersFacade), INourishmentListViewModel {

    init {

    }


    override val nourishment = MutableLiveData<List<Nourishment>>()

    override fun createNewButtonClicked() {
        //open new activity
    }

    override fun nourishmentClicked(date: LocalDate) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}