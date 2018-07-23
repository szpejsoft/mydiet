package com.szpejsoft.mydiet.views.settings

import com.jakewharton.rxrelay2.BehaviorRelay
import com.szpejsoft.mydiet.domain.Settings
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class MockSettingsRepository : ISettingsRepository {
    var fruits = 5
    var vegetables = 5
    var grain = 3
    var dairy = 4
    var meat = 2
    var fat = 1
    var interval = 180

    private val settingsObservable: BehaviorRelay<Settings> = BehaviorRelay.create()

    init {
        settingsObservable.accept(Settings(id = 0,
                allowedFruitPortions = fruits,
                allowedVegetablePortions = vegetables,
                allowedGrainPortions = grain,
                allowedDairyPortions = dairy,
                allowedMeatPortions = meat,
                allowedFatPortions = fat,
                intervalBetweenMeals = interval))
    }

    override val getSettings: Observable<Settings>
        get() = settingsObservable

    override fun saveSettings(settings: Settings): Completable =
            Completable.fromAction {
                fruits = settings.allowedFruitPortions
                vegetables = settings.allowedVegetablePortions
                grain = settings.allowedGrainPortions
                dairy = settings.allowedDairyPortions
                meat = settings.allowedMeatPortions
                fat = settings.allowedFatPortions
                interval = settings.intervalBetweenMeals
                settingsObservable.accept(settings)
            }

}