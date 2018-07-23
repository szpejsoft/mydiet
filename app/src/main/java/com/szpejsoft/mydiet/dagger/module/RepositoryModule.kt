package com.szpejsoft.mydiet.dagger.module

import com.szpejsoft.mydiet.views.settings.ISettingsRepository
import com.szpejsoft.mydiet.views.settings.MockSettingsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSettingsRepository() : ISettingsRepository = MockSettingsRepository()
}