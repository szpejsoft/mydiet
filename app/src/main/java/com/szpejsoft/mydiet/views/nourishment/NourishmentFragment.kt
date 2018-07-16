package com.szpejsoft.mydiet.views.nourishment


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.szpejsoft.mydiet.R
import kotlinx.android.synthetic.main.nourishments_layout.*

class NourishmentFragment : Fragment() {
    private lateinit var nourishmentViewModel: INourishmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nourishmentViewModel = NourishmentViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.nourishments_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nourishmentViewModel.fruitsEatenAllowedData.observe(this, Observer { pair -> setFruitPortions(pair?.first, pair?.second) })
    }

    private fun setFruitPortions(eaten: Int?, allowed: Int?) {
        fruitsPortionCounter.setPortions(eaten ?: 0, allowed ?: 0)
    }
}