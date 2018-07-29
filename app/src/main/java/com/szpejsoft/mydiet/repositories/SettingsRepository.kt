package com.szpejsoft.mydiet.repositories

import com.szpejsoft.mydiet.db.settings.SettingsDao
import com.szpejsoft.mydiet.domain.DATE_FORMAT
import com.szpejsoft.mydiet.domain.Settings
import com.szpejsoft.mydiet.domain.toDomain
import com.szpejsoft.mydiet.domain.toEntity
import io.reactivex.Completable
import io.reactivex.Single
import org.joda.time.LocalDate
import javax.inject.Inject

class SettingsRepository
@Inject
constructor(private val settingsDao: SettingsDao) : ISettingsRepository {
    override fun getSettingsForDate(date: LocalDate): Single<Settings> =
            settingsDao.getSettingsForDate(date.toString(DATE_FORMAT))
                    .map { it.toDomain() }
                    .toSingle(DEFAULT_SETTINGS)

    override fun saveSettings(settings: Settings): Completable =
            Completable.fromAction { settingsDao.insertOrUpdate(settings.toEntity()) }

    companion object {
        private val DEFAULT_SETTINGS = Settings()
    }
}