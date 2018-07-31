package com.szpejsoft.mydiet.repositories

import com.szpejsoft.mydiet.dataproviders.SettingsDataProvider
import com.szpejsoft.mydiet.domain.Settings
import io.reactivex.Completable
import io.reactivex.Single
import org.joda.time.LocalDate
import javax.inject.Inject

class SettingsRepositoryImpl
@Inject
constructor(private val settingsDataProvider: SettingsDataProvider) : SettingsRepository {
    override fun getSettingsForDate(date: LocalDate): Single<Settings> =
            settingsDataProvider.getSettingsForDate(date)

    override fun saveSettings(settings: Settings): Completable =
            settingsDataProvider.insertOrUpdate(settings)
}