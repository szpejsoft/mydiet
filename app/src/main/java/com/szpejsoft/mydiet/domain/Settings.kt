package com.szpejsoft.mydiet.domain

data class Settings(val id: Int? = null,
                    val allowedFruitPortions: Int = 0,
                    val allowedVegetablePortions: Int = 0,
                    val allowedGrainPortions: Int = 0,
                    val allowedDairyPortions: Int = 0,
                    val allowedMeatPortions: Int = 0,
                    val allowedFatPortions: Int = 0,
                    val intervalBetweenMeals: Int = 0)