package com.szpejsoft.mydiet.dataproviders

import com.szpejsoft.mydiet.domain.Settings
import io.reactivex.Completable
import io.reactivex.Single
import org.joda.time.LocalDate


interface SettingsDataProvider {
    fun insertOrUpdate(settings: Settings): Completable
    fun deleteAll(): Completable
    fun delete(settings: Settings): Completable
    fun getSettingsForDate(date: LocalDate): Single<Settings>
}