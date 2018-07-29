package com.szpejsoft.mydiet.db.weight

import android.arch.persistence.room.*
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface WeightDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(weightEntity: WeightEntity)

    @Query("DELETE FROM weight_table")
    fun deleteAll()

    @Delete
    fun delete(weightEntity: WeightEntity)

    @Query("SELECT * from weight_table ORDER BY date ASC")
    fun selectAll(): Single<List<WeightEntity>>

    @Query("SELECT * FROM weight_table WHERE date <= :date ORDER BY date LIMIT 1")
    fun getWeightForDate(date: String): Maybe<WeightEntity>
}