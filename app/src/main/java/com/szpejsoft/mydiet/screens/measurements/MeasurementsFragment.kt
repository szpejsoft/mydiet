package com.szpejsoft.mydiet.screens.measurements

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.szpejsoft.mydiet.R

class MeasurementsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.measurements_layout, container, false)
    }
   //TODO wykresy  http://www.android-graphview.org/ albo https://github.com/PhilJay/MPAndroidChart (ma wtyczkę do Realma)
}