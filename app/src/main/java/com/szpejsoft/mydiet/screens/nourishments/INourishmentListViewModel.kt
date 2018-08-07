package com.szpejsoft.mydiet.screens.nourishments

import android.arch.lifecycle.LiveData
import com.szpejsoft.mydiet.domain.Nourishment
import org.joda.time.LocalDate

interface INourishmentListViewModel {
    val nourishment: LiveData<List<Nourishment>>
    fun createNewButtonClicked()
    fun nourishmentClicked(date: LocalDate)
}