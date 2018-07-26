package com.szpejsoft.mydiet.db.nourishment

import android.arch.persistence.room.*
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface NourishmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(nourishmentEntity: NourishmentEntity)

    @Query("DELETE FROM nourishment_table")
    fun deleteAll()

    @Query("SELECT * from nourishment_table ORDER BY date ASC")
    fun selectAll(): Flowable<List<NourishmentEntity>>

    @Query("SELECT * FROM nourishment_table WHERE date = :date")
    fun selectByDate(date: String): Maybe<NourishmentEntity>

    @Update
    fun update(nourishmentEntity: NourishmentEntity)

}