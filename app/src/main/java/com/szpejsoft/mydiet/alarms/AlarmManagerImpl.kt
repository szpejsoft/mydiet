package com.szpejsoft.mydiet.alarms

import io.reactivex.Observable
import javax.inject.Inject

class AlarmManagerImpl
@Inject
constructor() : AlarmManager {
    override val isAlarmSet: Observable<Boolean>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun setAlarm(minutes: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}