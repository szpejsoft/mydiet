package com.szpejsoft.mydiet.repositories

import com.szpejsoft.mydiet.dataproviders.NourishmentDataProvider
import com.szpejsoft.mydiet.debugToLogcat
import com.szpejsoft.mydiet.domain.Nourishment
import io.reactivex.Completable
import io.reactivex.Observable
import org.joda.time.LocalDate
import javax.inject.Inject

class NourishmentRepositoryImpl
@Inject
constructor(private val nourishmentDataProvider: NourishmentDataProvider) : NourishmentRepository {

    override fun getNourishmentForDate(date: LocalDate): Observable<Nourishment> =
            nourishmentDataProvider.getNourishmentForDate(date).debugToLogcat("ptsz ndp getForDate")

    override fun getAllNourishment(): Observable<List<Nourishment>> =
            nourishmentDataProvider.getAllNourishment().debugToLogcat("ptsz dp ngetAll")


    override fun saveNourishment(nourishment: Nourishment): Completable =
            nourishmentDataProvider.save(nourishment).debugToLogcat("ptsz ndp save")
}