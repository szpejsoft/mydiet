package com.szpejsoft.mydiet.repositories

import com.szpejsoft.mydiet.dataproviders.SettingsDataProvider
import com.szpejsoft.mydiet.debugToLogcat
import com.szpejsoft.mydiet.domain.Settings
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.joda.time.LocalDate
import javax.inject.Inject

class SettingsRepositoryImpl
@Inject
constructor(private val settingsDataProvider: SettingsDataProvider) : SettingsRepository {

    override fun getSettingsForDate(date: LocalDate): Single<Settings> =
            settingsDataProvider.getSettingsForDate(date).debugToLogcat("ptsz sdp getForDate")

    override fun saveSettings(settings: Settings): Completable =
            settingsDataProvider.insertOrUpdate(settings).debugToLogcat("ptsz sdp save")
}