package com.szpejsoft.mydiet.db.height

import android.arch.persistence.room.*
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface HeightDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(heightEntity: HeightEntity)

    @Query("DELETE FROM height_table")
    fun deleteAll()

    @Delete
    fun delete(heightEntity: HeightEntity)

    @Query("SELECT * from height_table ORDER BY date ASC")
    fun selectAll(): Single<List<HeightEntity>>

    @Query("SELECT * FROM height_table WHERE date <= :date ORDER BY date DESC LIMIT 1 ")
    fun getHeightForDate(date: String): Maybe<HeightEntity>
}