package com.szpejsoft.mydiet.domain

import org.joda.time.LocalDate

data class Nourishment(
        val fruitsConsumed: Int = 0,
        val vegetablesConsumed: Int = 0,
        val grainConsumed: Int = 0,
        val dairyConsumed: Int = 0,
        val meatConsumed: Int = 0,
        val fatConsumed: Int = 0,
        val date: LocalDate = LocalDate.now()
)