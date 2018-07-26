package com.szpejsoft.mydiet.domain

import com.szpejsoft.mydiet.db.settings.SettingsEntity
import org.joda.time.LocalDate

fun Settings.toEntity() = SettingsEntity(
        date = this.date.toString(DATE_FORMAT),
        fruitPortionsAllowed = this.fruitPortionsAllowed,
        vegetablePortionsAllowed = this.vegetablePortionsAllowed,
        grainPortionsAllowed = this.grainPortionsAllowed,
        dairyPortionsAllowed = this.dairyPortionsAllowed,
        meatPortionsAllowed = this.meatPortionsAllowed,
        fatPortionsAllowed = this.fatPortionsAllowed,
        intervalBetweenMeals = this.intervalBetweenMeals)

fun Collection<Settings>.toEntity() = this.map { it.toEntity() }

fun SettingsEntity.toDomain() = Settings(
        date = LocalDate(this.date),
        fruitPortionsAllowed = this.fruitPortionsAllowed,
        vegetablePortionsAllowed = this.vegetablePortionsAllowed,
        grainPortionsAllowed = this.grainPortionsAllowed,
        dairyPortionsAllowed = this.dairyPortionsAllowed,
        meatPortionsAllowed = this.meatPortionsAllowed,
        fatPortionsAllowed = this.fatPortionsAllowed,
        intervalBetweenMeals = this.intervalBetweenMeals)

fun Collection<SettingsEntity>.toDomain() = this.map { it.toDomain() }