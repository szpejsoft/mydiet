package com.szpejsoft.mydiet.db.settings

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "settings_table")
class SettingsEntity(@PrimaryKey(autoGenerate = false)
                     val date: String,
                     val fruitPortionsAllowed: Int = 0,
                     val vegetablePortionsAllowed: Int = 0,
                     val grainPortionsAllowed: Int = 0,
                     val milkPortionsAllowed: Int = 0,
                     val meatPortionsAllowed: Int = 0,
                     val fatPortionsAllowed: Int = 0,
                     val intervalBetweenMeals: Int = 0)
