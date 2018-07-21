package com.szpejsoft.mydiet.views.settings

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import com.jakewharton.rxrelay2.BehaviorRelay
import com.szpejsoft.mydiet.MyDietFragment
import com.szpejsoft.mydiet.R
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
    }

    private fun observeViewModel() {
        val lifecycleOwner = this
        settingsViewModel.apply {
            fruitPortionsData.observe(lifecycleOwner, Observer { safeSetValue(fruitsPortionsEdit, it) })
            vegetablePortionsData.observe(lifecycleOwner, Observer { safeSetValue(vegetablesPortionEdit, it) })
            grainPortionsData.observe(lifecycleOwner, Observer { safeSetValue(grainPortionsEdit, it) })
            dairyPortionsData.observe(lifecycleOwner, Observer { safeSetValue(dairyPortionsEdit, it) })
            meatPortionsData.observe(lifecycleOwner, Observer { safeSetValue(meatPortionsEdit, it) })
            fatPortionsData.observe(lifecycleOwner, Observer { safeSetValue(fatPortionsEdit, it) })
            intervalBetweenMealsData.observe(lifecycleOwner, Observer { setIntervalValue(it) })
        }
    }

    private fun setIntervalValue(interval: Int?) {
        interval ?: return
        intervalTxt.text = formatInterval(interval)
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
        fruitsPortionsEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
        vegetablesPortionEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
        grainPortionsEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
        dairyPortionsEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
        meatPortionsEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
        fatPortionsEdit.setRange(MIN_PORTIONS, MAX_PORTIONS)
    }

    private fun setupValueEditsListeners() {
        settingsViewModel.apply {
            setAllowedFruitPortionsObservable(fruitsPortionsEdit.getValueObservable())
            setAllowedVegetablePortionsObservable(vegetablesPortionEdit.getValueObservable())
            setAllowedGrainPortionsObservable(grainPortionsEdit.getValueObservable())
            setAllowedDairyPortionsObservable(dairyPortionsEdit.getValueObservable())
            setAllowedMeatPortionsObservable(meatPortionsEdit.getValueObservable())
            setAllowedFatPortionsObservable(fatPortionsEdit.getValueObservable())
        }
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
            displayedValues = intervals.map { formatInterval(it) }.toTypedArray()
//            setOnValueChangedListener { _, _, newVal -> intervalRelay.accept(intervals[newVal]) }
        }
        val okButton: View = view.findViewById(R.id.okButton)
        okButton.setOnClickListener {
            intervalRelay.accept(intervals[intervalPicker.value])
            intervalTxt.text = formatInterval(intervals[intervalPicker.value])
            dialog.dismiss()
        }
        val cancelButton: View = view.findViewById(R.id.cancelButton)
        cancelButton.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun formatInterval(value: Int): String {
        val hours = "${value / 60}"
        val minutes = "${value % 60}".padStart(2, '0')
        return "$hours:$minutes"
    }

    companion object {
        const val MAX_PORTIONS = 10
        const val MIN_PORTIONS = 0
    }
}
