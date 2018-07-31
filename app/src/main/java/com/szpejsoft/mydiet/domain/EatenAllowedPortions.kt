package com.szpejsoft.mydiet.domain

data class EatenAllowedPortions(
        val fruitsConsumed: Int = 0,
        val fruitsMax: Int = 0,
        val vegetablesConsumed: Int = 0,
        val vegetablesMax: Int = 0,
        val grainConsumed: Int = 0,
        val grainMax: Int = 0,
        val dairyConsumed: Int = 0,
        val dairyMax: Int = 0,
        val meatConsumed: Int = 0,
        val meatMax: Int = 0,
        val fatConsumed: Int = 0,
        val fatMax: Int = 0)