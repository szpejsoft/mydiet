package com.szpejsoft.mydiet.dagger.module

import com.szpejsoft.mydiet.alarms.AlarmManager
import com.szpejsoft.mydiet.alarms.AlarmManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AlarmModule {
    @Provides
    @Singleton
    fun provideAlarmManager(): AlarmManager = AlarmManagerImpl()

}