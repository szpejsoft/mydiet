package com.szpejsoft.mydiet.db.weight

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "weight_table")
class WeightEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int?,
        val date: String,
        val weight: Float
)