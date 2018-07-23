package com.szpejsoft.mydiet.repositories

import com.jakewharton.rxrelay2.BehaviorRelay
import com.szpejsoft.mydiet.domain.Settings
import io.reactivex.Completable
import io.reactivex.Observable

class MockSettingsRepository : ISettingsRepository {
    private var fruits = 5
    private var vegetables = 5
    private var grain = 3
    private var dairy = 4
    private var meat = 2
    private var fat = 1
    private var interval = 180

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