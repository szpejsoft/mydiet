package com.szpejsoft.mydiet.domain

import com.szpejsoft.mydiet.db.height.HeightEntity
import org.joda.time.LocalDate

fun Height.toEntity() = HeightEntity(id = this.id,
        date = this.date.toString(DATE_FORMAT),
        height = this.height)

fun Collection<Height>.toEntity() = this.map { it.toEntity() }

fun HeightEntity.toDomain() = Height(id = this.id,
        date = LocalDate(this.date),
        height = this.height)

fun Collection<HeightEntity>.toDomain() = this.map { it.toDomain() }

