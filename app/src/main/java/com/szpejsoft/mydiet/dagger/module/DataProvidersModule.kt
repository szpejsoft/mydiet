package com.szpejsoft.mydiet.dagger.module

import com.szpejsoft.mydiet.dataproviders.NourishmentDataProvider
import com.szpejsoft.mydiet.dataproviders.NourishmentDataProviderImpl
import com.szpejsoft.mydiet.dataproviders.SettingsDataProvider
import com.szpejsoft.mydiet.dataproviders.SettingsDataProviderImpl
import com.szpejsoft.mydiet.db.MyDietDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataProvidersModule(private val myDietDatabase: MyDietDatabase?) {
    @Provides
    @Singleton
    fun provideSettingsDataProvider(): SettingsDataProvider =
            SettingsDataProviderImpl(myDietDatabase!!.settingsDao())

    @Provides
    @Singleton
    fun provideNourishmentDataProvider(): NourishmentDataProvider =
            NourishmentDataProviderImpl(myDietDatabase!!.nourishmentDao())


}