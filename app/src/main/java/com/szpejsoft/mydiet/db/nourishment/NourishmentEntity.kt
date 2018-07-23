package com.szpejsoft.mydiet.db.nourishment

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.joda.time.LocalDate

@Entity(tableName = "nourishment_table")
data class NourishmentEntity(
        @PrimaryKey(autoGenerate = false)
        val date: String,
        val fruitsConsumed: Int = 0,
        val vegetablesConsumed: Int = 0,
        val grainConsumed: Int = 0,
        val milkConsumed: Int = 0,
        val meatConsumed: Int = 0,
        val fatConsumed: Int = 0)