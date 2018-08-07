package com.szpejsoft.mydiet.db.nourishment

import android.arch.persistence.room.*
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface NourishmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(nourishmentEntity: NourishmentEntity)

    @Query("DELETE FROM nourishment_table")
    fun deleteAll()

    @Delete
    fun delete(nourishmentEntity: NourishmentEntity)

    @Query("SELECT * from nourishment_table ORDER BY date ASC")
    fun selectAll(): Flowable<List<NourishmentEntity>>

    @Query("SELECT * FROM nourishment_table WHERE date <= :date ORDER BY date DESC LIMIT 1 ")
    fun getNourishmentForDate(date: String): Maybe<NourishmentEntity>

    @Query("SELECT date FROM nourishment_table ORDER BY date ASC LIMIT 1")
    fun getFirstNourishmentDate(): Maybe<String>

}