package com.szpejsoft.mydiet.domain

import com.szpejsoft.mydiet.db.nourishment.NourishmentEntity
import org.joda.time.LocalDate

fun Nourishment.toEntity(): NourishmentEntity = NourishmentEntity(
        id = this.id,
        fruitsConsumed = this.fruitsConsumed,
        fruitsMax = this.fruitsMax,
        vegetablesConsumed = this.vegetablesConsumed,
        vegetablesMax = this.vegetablesMax,
        grainConsumed = this.grainConsumed,
        grainMax = this.grainMax,
        milkConsumed = this.milkConsumed,
        milkMax = this.milkMax,
        meatConsumed = this.meatConsumed,
        meatMax = this.meatMax,
        fatConsumed = this.fatConsumed,
        fatMax = this.fatMax,
        date = this.date.toString(DATE_FORMAT)
)

fun Collection<Nourishment>.toEntity() = this.map { it.toEntity() }

fun NourishmentEntity.toDomain() = Nourishment(
        id = this.id,
        fruitsConsumed = this.fruitsConsumed,
        fruitsMax = this.fruitsMax,
        vegetablesConsumed = this.vegetablesConsumed,
        vegetablesMax = this.vegetablesMax,
        grainConsumed = this.grainConsumed,
        grainMax = this.grainMax,
        milkConsumed = this.milkConsumed,
        milkMax = this.milkMax,
        meatConsumed = this.meatConsumed,
        meatMax = this.meatMax,
        fatConsumed = this.fatConsumed,
        fatMax = this.fatMax,
        date = LocalDate(this.date)
)

fun Collection<NourishmentEntity>.toDomain() = this.map { it.toDomain() }
