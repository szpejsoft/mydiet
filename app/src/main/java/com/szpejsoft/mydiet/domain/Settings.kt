package com.szpejsoft.mydiet.domain

import org.joda.time.LocalDate

data class Settings(val date: LocalDate = LocalDate.now(),
                    val fruitPortionsAllowed: Int = 0,
                    val vegetablePortionsAllowed: Int = 0,
                    val grainPortionsAllowed: Int = 0,
                    val dairyPortionsAllowed: Int = 0,
                    val meatPortionsAllowed: Int = 0,
                    val fatPortionsAllowed: Int = 0,
                    val intervalBetweenMeals: Int = 0)