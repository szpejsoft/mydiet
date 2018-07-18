package com.szpejsoft.mydiet.views.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.toast
import com.szpejsoft.mydiet.MyDietFragment
import com.szpejsoft.mydiet.R
import com.szpejsoft.mydiet.utils.SchedulersFacade
import kotlinx.android.synthetic.main.settings_layout.*
import javax.inject.Inject

class SettingsFragment : MyDietFragment() {
    @Inject
    lateinit var schedulersFacade: SchedulersFacade

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.settings_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        numberPicker.setRange(0, 10)
        numberPicker.setValue(-1)
        numberPicker.getValueObservable()
                .subscribeOn(schedulersFacade.computation())
                .observeOn(schedulersFacade.ui())
                .subscribe { context?.toast("value = $it") }
    }
}