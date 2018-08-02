package com.szpejsoft.mydiet.dataproviders

import com.szpejsoft.mydiet.db.settings.SettingsDao
import com.szpejsoft.mydiet.domain.DATE_FORMAT
import com.szpejsoft.mydiet.domain.Settings
import com.szpejsoft.mydiet.domain.toDomain
import com.szpejsoft.mydiet.domain.toEntity
import io.reactivex.Completable
import io.reactivex.Single
import org.joda.time.LocalDate
import javax.inject.Inject

class SettingsDataProviderImpl
@Inject
constructor(private val settingsDao: SettingsDao) : SettingsDataProvider {
    override fun insertOrUpdate(settings: Settings): Completable = Completable.fromAction {
        settingsDao.insertOrUpdate(settings.toEntity())
    }

    override fun deleteAll(): Completable = Completable.fromAction { settingsDao.deleteAll() }

    override fun delete(settings: Settings): Completable =
            Completable.fromAction { settingsDao.delete(settings.toEntity()) }

    override fun getSettingsForDate(date: LocalDate): Single<Settings> =
            settingsDao.getSettingsForDate(date.toString(DATE_FORMAT))
                    .map { it.toDomain() }
                    .toSingle(DEFAULT_SETTINGS)

    companion object {
        private val DEFAULT_SETTINGS = Settings()
    }
}