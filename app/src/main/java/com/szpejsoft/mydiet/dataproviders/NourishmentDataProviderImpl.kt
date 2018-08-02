package com.szpejsoft.mydiet.dataproviders

import com.szpejsoft.mydiet.db.nourishment.NourishmentDao
import com.szpejsoft.mydiet.domain.DATE_FORMAT
import com.szpejsoft.mydiet.domain.Nourishment
import com.szpejsoft.mydiet.domain.toDomain
import com.szpejsoft.mydiet.domain.toEntity
import io.reactivex.Completable
import io.reactivex.Single
import org.joda.time.LocalDate

class NourishmentDataProviderImpl(private val nourishmentDao: NourishmentDao
) : NourishmentDataProvider {
    override fun save(nourishment: Nourishment): Completable =
            Completable.fromAction { nourishmentDao.insertOrUpdate(nourishment.toEntity()) }

    override fun deleteAll(): Completable = Completable.fromAction { nourishmentDao.deleteAll() }

    override fun delete(nourishment: Nourishment): Completable =
            Completable.fromAction { nourishmentDao.delete(nourishment.toEntity()) }

    override fun getNourishmentForDate(date: LocalDate): Single<Nourishment> =
            nourishmentDao.getNourishmentForDate(date.toString(DATE_FORMAT)).map { it.toDomain() }.toSingle()


}

