package com.szpejsoft.mydiet.screens.settings

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxrelay2.BehaviorRelay
import com.szpejsoft.mydiet.MyDietFragment
import com.szpejsoft.mydiet.R
import com.szpejsoft.mydiet.formatInterval
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : MyDietFragment() {

    private val settingsViewModel: ISettingsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SettingsViewModel::class.java)
    }

    private val intervalRelay: BehaviorRelay<Int> = BehaviorRelay.create()

    private lateinit var intervals: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppComponent().inject(this)
        intervals = resources.getIntArray(R.array.intervals)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPortionEdits()
        setupIntervalEdit()
        observeViewModel()
        settingsViewModel.onSaveBtnClicked(RxView.clicks(saveButton))
    }

    private fun setIntervalValue(interval: Int?) {
        interval ?: return
        intervalTxt.text = interval.formatInterval()
    }

    private fun safeSetValue(numberEdit: NumberEdit?, value: Int?) {
        value ?: return
        numberEdit?.setValue(value)
    }

    private fun setupPortionEdits() {
        setupValueEditsRanges()
        setupValueEditsListeners()
    }

    private fun setupValueEditsRanges() {
        fruitPortionsEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
        vegetablePortionEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
        grainPortionsEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
        dairyPortionsEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
        meatPortionsEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
        fatPortionsEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
    }

    private fun setupValueEditsListeners() {
        settingsViewModel.apply {
            setAllowedFruitPortionsObservable(fruitPortionsEdit.getValueObservable())
            setAllowedVegetablePortionsObservable(vegetablePortionEdit.getValueObservable())
            setAllowedGrainPortionsObservable(grainPortionsEdit.getValueObservable())
            setAllowedDairyPortionsObservable(dairyPortionsEdit.getValueObservable())
            setAllowedMeatPortionsObservable(meatPortionsEdit.getValueObservable())
            setAllowedFatPortionsObservable(fatPortionsEdit.getValueObservable())
            setIntervalBetweenMealsObservable(intervalRelay)
        }
    }

    private fun observeViewModel() {
        settingsViewModel.apply {
            fruitPortionsData.observe(this@SettingsFragment, Observer { safeSetValue(fruitPortionsEdit, it) })
            vegetablePortionsData.observe(this@SettingsFragment, Observer { safeSetValue(vegetablePortionEdit, it) })
            grainPortionsData.observe(this@SettingsFragment, Observer { safeSetValue(grainPortionsEdit, it) })
            dairyPortionsData.observe(this@SettingsFragment, Observer { safeSetValue(dairyPortionsEdit, it) })
            meatPortionsData.observe(this@SettingsFragment, Observer { safeSetValue(meatPortionsEdit, it) })
            fatPortionsData.observe(this@SettingsFragment, Observer { safeSetValue(fatPortionsEdit, it) })
            intervalBetweenMealsData.observe(this@SettingsFragment, Observer { setIntervalValue(it) })
            saveButtonEnabledData.observe(this@SettingsFragment, Observer { setSaveButtonEnabled(it) })
        }
    }

    private fun setSaveButtonEnabled(enabled: Boolean?) {
        saveButton.isEnabled = enabled ?: true
    }

    private fun setupIntervalEdit() {
        intervalTxt.setOnClickListener { showIntervalEditDialog() }
    }

    private fun showIntervalEditDialog() {
        val myActivity = activity
        myActivity ?: return

        val view = View.inflate(myActivity, R.layout.settings_interval_edit_dialog, null)
        val dialog = AlertDialog.Builder(myActivity)
                .setView(view)
                .setCancelable(false)
                .create()
        //TODO zobaczyć czy nie da się przez kotlinowe referencje do widoków
        val intervalPicker: NumberPicker = view.findViewById(R.id.intervalPicker)
        intervalPicker.apply {
            minValue = 0
            maxValue = intervals.size - 1
            displayedValues = intervals.map { it.formatInterval() }.toTypedArray()
            wrapSelectorWheel = false
        }
        val okButton: View = view.findViewById(R.id.okButton)
        okButton.setOnClickListener {
            intervalRelay.accept(intervals[intervalPicker.value])
            intervalTxt.text = intervals[intervalPicker.value].formatInterval()
            dialog.dismiss()
        }
        val cancelButton: View = view.findViewById(R.id.cancelButton)
        cancelButton.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }


    companion object {
        const val MAX_PORTIONS = 10
        const val MIN_PORTIONS = 0
    }
}
