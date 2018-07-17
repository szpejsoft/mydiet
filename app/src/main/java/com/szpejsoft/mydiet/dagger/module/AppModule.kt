package com.szpejsoft.mydiet.dagger.module

import android.app.Application
import android.content.Context
import com.szpejsoft.mydiet.MyDietApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: MyDietApplication) {

    @Singleton
    @Provides
    internal fun provideContext(): Context = application.applicationContext


    @Singleton
    @Provides
    internal fun provideApplication(): Application = application

    @Singleton
    @Provides
    internal fun provideXMobileApplication(): MyDietApplication = application


}