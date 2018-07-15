package com.szpejsoft.mydiet.domain

import org.joda.time.LocalDate

data class Height(val id: Int? = null,
                  val date: LocalDate = LocalDate.now(),
                  val height: Float = 0.0f)