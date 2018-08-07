package com.szpejsoft.mydiet

import android.os.Bundle
import com.szpejsoft.mydiet.screens.measurements.MeasurementsFragment
import com.szpejsoft.mydiet.screens.nourishments.NourishmentFragment
import com.szpejsoft.mydiet.screens.settings.SettingsFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : MyDietActivity() {
    private lateinit var settingsFragment: SettingsFragment
    private lateinit var nourishmentFragment: NourishmentFragment
    private lateinit var measurementsFragment: MeasurementsFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
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
        val frag = supportFragmentManager.findFragmentByTag(SETTINGS_FRAGMENT_TAG)
        if (frag != null) {
            return true
        }
        supportFragmentManager.beginTransaction()
                .replace(R.id.contentView, settingsFragment, SETTINGS_FRAGMENT_TAG)
                .commit()
        return true
    }

    private fun showMeasurements(): Boolean {
        val frag = supportFragmentManager.findFragmentByTag(MEASUREMENTS_FRAGMENT_TAG)
        if (frag != null) {
            return true
        }
        supportFragmentManager.beginTransaction()
                .replace(R.id.contentView, measurementsFragment, MEASUREMENTS_FRAGMENT_TAG)
                .commit()
        return true
    }

    private fun showNourishment(): Boolean {
        val frag = supportFragmentManager.findFragmentByTag(NOURISHMENT_FRAGMENT_TAG)
        if (frag != null) {
            return true
        }
        supportFragmentManager.beginTransaction()
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
