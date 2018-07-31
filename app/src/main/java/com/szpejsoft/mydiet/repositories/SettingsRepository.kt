package com.szpejsoft.mydiet.repositories

import com.szpejsoft.mydiet.domain.Settings
import io.reactivex.Completable
import io.reactivex.Single
import org.joda.time.LocalDate

interface SettingsRepository {
    fun getSettingsForDate(date: LocalDate): Single<Settings>
    fun saveSettings(settings: Settings): Completable
}
