package com.szpejsoft.mydiet.db.height

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "height_table")
class HeightEntity(
        @PrimaryKey(autoGenerate = false)
        val date: String,
        val height: Float
)