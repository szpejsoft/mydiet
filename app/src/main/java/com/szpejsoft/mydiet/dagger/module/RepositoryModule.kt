package com.szpejsoft.mydiet.dagger.module

import com.szpejsoft.mydiet.repositories.INourishmentRepository
import com.szpejsoft.mydiet.repositories.ISettingsRepository
import com.szpejsoft.mydiet.repositories.MockNourishmentRepository
import com.szpejsoft.mydiet.repositories.MockSettingsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSettingsRepository(): ISettingsRepository = MockSettingsRepository()

    @Provides
    @Singleton
    fun provideNourishmentRepository(): INourishmentRepository = MockNourishmentRepository()
}