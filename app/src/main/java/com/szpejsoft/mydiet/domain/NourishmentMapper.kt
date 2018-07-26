package com.szpejsoft.mydiet.domain

import com.szpejsoft.mydiet.db.nourishment.NourishmentEntity
import org.joda.time.LocalDate

fun Nourishment.toEntity(): NourishmentEntity = NourishmentEntity(
        date = this.date.toString(DATE_FORMAT),
        fruitsConsumed = this.fruitsConsumed,
        vegetablesConsumed = this.vegetablesConsumed,
        grainConsumed = this.grainConsumed,
        milkConsumed = this.milkConsumed,
        meatConsumed = this.meatConsumed,
        fatConsumed = this.fatConsumed)

fun Collection<Nourishment>.toEntity() = this.map { it.toEntity() }

fun NourishmentEntity.toDomain() = Nourishment(
        date = LocalDate(this.date),
        fruitsConsumed = this.fruitsConsumed,
        vegetablesConsumed = this.vegetablesConsumed,
        grainConsumed = this.grainConsumed,
        milkConsumed = this.milkConsumed,
        meatConsumed = this.meatConsumed,
        fatConsumed = this.fatConsumed)

fun Collection<NourishmentEntity>.toDomain() = this.map { it.toDomain() }
