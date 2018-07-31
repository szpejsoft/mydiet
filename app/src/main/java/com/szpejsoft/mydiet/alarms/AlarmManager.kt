package com.szpejsoft.mydiet.alarms

import io.reactivex.Observable

interface AlarmManager {
    val isAlarmSet: Observable<Boolean>

    fun setAlarm(minutes: Int)

}