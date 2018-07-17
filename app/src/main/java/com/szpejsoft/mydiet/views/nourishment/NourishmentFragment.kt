package com.szpejsoft.mydiet.views.nourishment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.szpejsoft.mydiet.MyDietFragment
import com.szpejsoft.mydiet.R
import kotlinx.android.synthetic.main.nourishments_layout.*
import javax.inject.Inject

class NourishmentFragment : MyDietFragment() {

    private val nourishmentViewModel: INourishmentViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(NourishmentViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.nourishments_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        nourishmentViewModel.dairyEatenAllowedData.observe(this, Observer { portions -> setPortions(portions, dairyPortionCounter) })
        nourishmentViewModel.fatEatenAllowedData.observe(this, Observer { portions -> setPortions(portions, fatPortionCounter) })
        nourishmentViewModel.fruitsEatenAllowedData.observe(this, Observer { portions -> setPortions(portions, fruitsPortionCounter) })
        nourishmentViewModel.grainEatenAllowedData.observe(this, Observer { portions -> setPortions(portions, grainProductsPortionCounter) })
        nourishmentViewModel.vegetablesEatenAllowedData.observe(this, Observer { portions -> setPortions(portions, vegetablesPortionCounter) })
        nourishmentViewModel.meatEatenAllowedData.observe(this, Observer { portions -> setPortions(portions, meatPortionCounter) })
        nourishmentViewModel.nextMealWaitingTime.observe(this, Observer { minutesToNextMeal -> setNextAlertButtonTitle(minutesToNextMeal) })
    }

    private fun setNextAlertButtonTitle(minutes: String?) {
        nextMealAlertButton.text = context?.getString(R.string.nourishment_next_alarm, minutes)
    }

    private fun setPortions(portions: Pair<Int, Int>?, portionCounter: PortionCounter) {
        portionCounter.setPortions(portions?.first ?: 0, portions?.second ?: 0)
    }


}