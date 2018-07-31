package com.szpejsoft.mydiet.repositories

import com.szpejsoft.mydiet.domain.Settings
import io.reactivex.Completable
import io.reactivex.Single
import org.joda.time.LocalDate

class MockSettingsRepository : SettingsRepository {
    private var fruits = 5
    private var vegetables = 5
    private var grain = 3
    private var dairy = 4
    private var meat = 2
    private var fat = 1
    private var interval = 180

    override fun getSettingsForDate(date: LocalDate): Single<Settings> = Single.just(
            Settings(date = date,
                    fruitPortionsAllowed = fruits,
                    vegetablePortionsAllowed = vegetables,
                    grainPortionsAllowed = grain,
                    dairyPortionsAllowed = dairy,
                    meatPortionsAllowed = meat,
                    fatPortionsAllowed = fat,
                    intervalBetweenMeals = interval)
    )

    override fun saveSettings(settings: Settings): Completable =
            Completable.fromAction {
                fruits = settings.fruitPortionsAllowed
                vegetables = settings.vegetablePortionsAllowed
                grain = settings.grainPortionsAllowed
                dairy = settings.dairyPortionsAllowed
                meat = settings.meatPortionsAllowed
                fat = settings.fatPortionsAllowed
                interval = settings.intervalBetweenMeals
            }
}
