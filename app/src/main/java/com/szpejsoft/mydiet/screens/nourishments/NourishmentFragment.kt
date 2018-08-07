package com.szpejsoft.mydiet.screens.nourishments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.toast
import com.szpejsoft.mydiet.MyDietFragment
import com.szpejsoft.mydiet.R
import com.szpejsoft.mydiet.domain.MealAlarm
import com.szpejsoft.mydiet.domain.Portions
import kotlinx.android.synthetic.main.nourishment_layout.*
import org.joda.time.LocalDate

class NourishmentFragment : MyDietFragment() {

    private val nourishmentViewModel: INourishmentViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(NourishmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.nourishment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        //TODO to tylko do testów
        nourishmentViewModel.setDate(LocalDate())
    }

    private fun observeViewModel() {
        nourishmentViewModel.apply {
            dairyPortionsData.observe(this@NourishmentFragment, Observer { portions -> setPortions(portions, dairyPortionCounter) })
            fatPortionsData.observe(this@NourishmentFragment, Observer { portions -> setPortions(portions, fatPortionCounter) })
            fruitPortionsData.observe(this@NourishmentFragment, Observer { portions -> setPortions(portions, fruitsPortionCounter) })
            grainPortionsData.observe(this@NourishmentFragment, Observer { portions -> setPortions(portions, grainProductsPortionCounter) })
            vegetablePortionsData.observe(this@NourishmentFragment, Observer { portions -> setPortions(portions, vegetablesPortionCounter) })
            meatPortionsData.observe(this@NourishmentFragment, Observer { portions -> setPortions(portions, meatPortionCounter) })
            nextMealIntervalData.observe(this@NourishmentFragment, Observer { minutesToNextMeal -> setNextAlertButtonText(minutesToNextMeal) })
            nextMealAlarmSet.observe(this@NourishmentFragment, Observer { mealAlarm -> showNextMealAlarm(mealAlarm) })
            showSetNextAlarmButton.observe(this@NourishmentFragment, Observer { show -> showSetNextMealAlarmButton(show) })
        }
    }


    private fun setPortions(portions: Portions?, portionCounter: PortionCounter) {
        portions ?: return
        portionCounter.setPortions(portions.eaten, portions.allowed)
    }

    private fun setNextAlertButtonText(minutes: String?) {
        nextMealAlertButton.text = context?.getString(R.string.nourishment_next_alarm, minutes)
    }

    private fun showNextMealAlarm(mealAlarm: MealAlarm?) {
        mealAlarm ?: return
        activity?.toast(mealAlarm.message)
    }

    private fun showSetNextMealAlarmButton(show: Boolean?) {
        val show = show ?: true
        nextMealAlertButton.isVisible = show
    }

}