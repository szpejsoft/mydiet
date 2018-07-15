package com.szpejsoft.mydiet

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.szpejsoft.mydiet.views.measurements.MeasurementsFragment
import com.szpejsoft.mydiet.views.nourishment.NourishmentFragment
import com.szpejsoft.mydiet.views.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var settingsFragment: SettingsFragment
    lateinit var nourishmentFragment: NourishmentFragment
    lateinit var measurementsFragment: MeasurementsFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_nourishment -> showNourishment()
                R.id.action_measurements -> showMeasurements()
                R.id.action_settings -> showSettings()
                else -> false
            }
        }
        settingsFragment = SettingsFragment()
        nourishmentFragment = NourishmentFragment()
        measurementsFragment = MeasurementsFragment()
        bottomNavigation.selectedItemId = R.id.action_nourishment
    }

    private fun showSettings(): Boolean {
        val frag = fragmentManager.findFragmentByTag(SETTINGS_FRAGMENT_TAG)
        if (frag != null) {
            return true
        }
        fragmentManager.beginTransaction()
                .replace(R.id.contentView, settingsFragment, SETTINGS_FRAGMENT_TAG)
                .commit()
        return true
    }

    private fun showMeasurements(): Boolean {
        val frag = fragmentManager.findFragmentByTag(MEASUREMENTS_FRAGMENT_TAG)
        if (frag != null) {
            return true
        }
        fragmentManager.beginTransaction()
                .replace(R.id.contentView, measurementsFragment, MEASUREMENTS_FRAGMENT_TAG)
                .commit()
        return true
    }

    private fun showNourishment(): Boolean {
        val frag = fragmentManager.findFragmentByTag(NOURISHMENT_FRAGMENT_TAG)
        if (frag != null) {
            return true
        }
        fragmentManager.beginTransaction()
                .replace(R.id.contentView, nourishmentFragment, NOURISHMENT_FRAGMENT_TAG)
                .commit()
        return true
    }

    companion object {
        const val SETTINGS_FRAGMENT_TAG = "SETTINGS_FRAGMENT"
        const val NOURISHMENT_FRAGMENT_TAG = "NOURISHMENT_FRAGMENT"
        const val MEASUREMENTS_FRAGMENT_TAG = "MEASUREMENTS_FRAGMENT"
    }
}
