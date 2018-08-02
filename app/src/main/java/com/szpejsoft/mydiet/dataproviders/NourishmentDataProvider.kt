package com.szpejsoft.mydiet.dataproviders

import com.szpejsoft.mydiet.domain.Nourishment
import io.reactivex.Completable
import io.reactivex.Single
import org.joda.time.LocalDate

interface NourishmentDataProvider {
    fun save(nourishment: Nourishment): Completable

    fun deleteAll(): Completable

    fun delete(nourishment: Nourishment): Completable

    fun getNourishmentForDate(date: LocalDate): Single<Nourishment>
}
