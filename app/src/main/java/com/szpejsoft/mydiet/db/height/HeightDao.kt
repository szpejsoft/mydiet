package com.szpejsoft.mydiet.db.height

import android.arch.persistence.room.*
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface HeightDao {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(heightEntity: HeightEntity)

    @Query("DELETE FROM height_table")
    fun deleteAll()

    @Query("SELECT * from height_table ORDER BY date ASC")
    fun selectAll(): Single<List<HeightEntity>>

    @Query("SELECT * FROM height_table WHERE date = :date")
    fun selectByDate(date: String): Maybe<HeightEntity>

    @Query("SELECT * FROM height_table WHERE date < :date ORDER BY date LIMIT 1")
    fun findLastHeightForDate(date: String): Single<HeightEntity>

    @Update
    fun update(heightEntity: HeightEntity)

}