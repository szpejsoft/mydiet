package com.szpejsoft.mydiet.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.szpejsoft.mydiet.db.height.HeightDao
import com.szpejsoft.mydiet.db.height.HeightEntity
import com.szpejsoft.mydiet.db.nourishment.NourishmentDao
import com.szpejsoft.mydiet.db.nourishment.NourishmentEntity
import com.szpejsoft.mydiet.db.settings.SettingsDao
import com.szpejsoft.mydiet.db.settings.SettingsEntity
import com.szpejsoft.mydiet.db.weight.WeightEntity

@Database(entities = [(NourishmentEntity::class), (HeightEntity::class), (WeightEntity::class), (SettingsEntity::class)], version = 1)
abstract class MyDietDatabase : RoomDatabase() {
    abstract fun nourishmentDao(): NourishmentDao
    abstract fun heightDao(): HeightDao
    abstract fun settingsDao(): SettingsDao

    companion object {
        private var INSTANCE: MyDietDatabase? = null

        fun getInstance(context: Context): MyDietDatabase? {
            if (INSTANCE == null) {
                synchronized(MyDietDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            MyDietDatabase::class.java, "mydiet.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}