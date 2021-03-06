package com.szpejsoft.mydiet.db.settings

import android.arch.persistence.room.*
import io.reactivex.Completable
import io.reactivex.Maybe
import org.joda.time.LocalDate

@Dao
interface SettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(settings: SettingsEntity)

    @Query("DELETE FROM settings_table")
    fun deleteAll()

    @Delete
    fun delete(settings: SettingsEntity)

    @Query("SELECT * FROM settings_table WHERE date <= :date ORDER BY date DESC LIMIT 1")
    fun getSettingsForDate(date: String): Maybe<SettingsEntity>
}