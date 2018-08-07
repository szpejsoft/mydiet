package com.szpejsoft.mydiet.repositories

import com.szpejsoft.mydiet.domain.Nourishment
import io.reactivex.Completable
import io.reactivex.Observable
import org.joda.time.LocalDate

interface NourishmentRepository {
    fun getNourishmentForDate(date: LocalDate): Observable<Nourishment>
    fun saveNourishment(nourishment: Nourishment): Completable
    fun getAllNourishment(): Observable<List<Nourishment>>
}