package com.szpejsoft.mydiet.dagger.module

import com.szpejsoft.mydiet.dataproviders.NourishmentDataProvider
import com.szpejsoft.mydiet.dataproviders.SettingsDataProvider
import com.szpejsoft.mydiet.repositories.NourishmentRepository
import com.szpejsoft.mydiet.repositories.NourishmentRepositoryImpl
import com.szpejsoft.mydiet.repositories.SettingsRepository
import com.szpejsoft.mydiet.repositories.SettingsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSettingsRepository(settingsDataProvider: SettingsDataProvider): SettingsRepository =
            SettingsRepositoryImpl(settingsDataProvider)

    @Provides
    @Singleton
    fun provideNourishmentRepository(nourishmentDataProvider: NourishmentDataProvider): NourishmentRepository =
            NourishmentRepositoryImpl(nourishmentDataProvider)
}