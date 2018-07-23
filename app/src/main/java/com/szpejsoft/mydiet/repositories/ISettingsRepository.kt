package com.szpejsoft.mydiet.repositories

import com.szpejsoft.mydiet.domain.Settings
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


interface ISettingsRepository {
    val getSettings: Observable<Settings>
    fun saveSettings(settings: Settings): Completable
}