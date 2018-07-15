package com.szpejsoft.mydiet.domain

import org.joda.time.LocalDate

data class Weight(val id: Int? = null,
                  val date: LocalDate = LocalDate.now(),
                  val weight: Float = 0.0f)