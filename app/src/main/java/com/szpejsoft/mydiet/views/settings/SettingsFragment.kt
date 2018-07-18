package com.szpejsoft.mydiet.views.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.szpejsoft.mydiet.MyDietFragment
import com.szpejsoft.mydiet.R
import com.szpejsoft.mydiet.utils.SchedulersFacade
import kotlinx.android.synthetic.main.settings_layout.*
import javax.inject.Inject

class SettingsFragment : MyDietFragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.settings_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupValueEditsRanges()
    }

    private fun setupValueEditsRanges() {
        fruitsPortionsEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
        vegetablesPortionEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
        grainPortionsEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
        dairyPortionsEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
        meatPortionsEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
        fatPortionsEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
    }

    companion object {
        const val MAX_PORTIONS = 10
        const val MIN_PORTIONS = 0
    }
}