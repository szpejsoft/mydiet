package com.szpejsoft.mydiet.domain

import com.szpejsoft.mydiet.db.weight.WeightEntity
import org.joda.time.LocalDate

fun Weight.toEntity() = WeightEntity(date = this.date.toString(DATE_FORMAT),
        weight = this.weight)

fun Collection<Weight>.toEntity() = this.map { it.toEntity() }

fun WeightEntity.toDomain() = Weight(date = LocalDate(this.date),
        weight = this.weight)

fun Collection<WeightEntity>.toDomain() = this.map { it.toDomain() }

