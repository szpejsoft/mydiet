package com.szpejsoft.mydiet.dagger.module

import com.szpejsoft.mydiet.utils.SchedulersFacade
import com.szpejsoft.mydiet.utils.SchedulersProd
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SchedulersModule {
    @Provides
    @Singleton
    fun provideSchedulers(): SchedulersFacade = SchedulersProd()
}