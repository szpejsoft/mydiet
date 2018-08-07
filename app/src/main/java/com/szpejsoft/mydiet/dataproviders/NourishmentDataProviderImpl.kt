package com.szpejsoft.mydiet.dataproviders

import com.szpejsoft.mydiet.db.nourishment.NourishmentDao
import com.szpejsoft.mydiet.debugToLogcat
import com.szpejsoft.mydiet.domain.DATE_FORMAT
import com.szpejsoft.mydiet.domain.Nourishment
import com.szpejsoft.mydiet.domain.toDomain
import com.szpejsoft.mydiet.domain.toEntity
import io.reactivex.Completable
import io.reactivex.Observable
import org.joda.time.LocalDate

class NourishmentDataProviderImpl(private val nourishmentDao: NourishmentDao
) : NourishmentDataProvider {

    override fun save(nourishment: Nourishment): Completable =
            Completable.fromAction { nourishmentDao.insertOrUpdate(nourishment.toEntity()) }.debugToLogcat("ptsz ndp save")

    override fun deleteAll(): Completable = Completable.fromAction { nourishmentDao.deleteAll() }.debugToLogcat("ptsz ndp deleteAll")

    override fun delete(nourishment: Nourishment): Completable =
            Completable.fromAction { nourishmentDao.delete(nourishment.toEntity()) }.debugToLogcat("ptsz ndp delete")

    override fun getNourishmentForDate(date: LocalDate): Observable<Nourishment> =
            nourishmentDao.getNourishmentForDate(date.toString(DATE_FORMAT))
                    .map { it.toDomain() }
                    .toObservable().debugToLogcat("ptsz ndp getForDate")

    override fun getAllNourishment(): Observable<List<Nourishment>> =
            nourishmentDao.selectAll()
                    .map { it.toDomain() }
                    .toObservable().debugToLogcat("ptsz ndp getAll")


}

