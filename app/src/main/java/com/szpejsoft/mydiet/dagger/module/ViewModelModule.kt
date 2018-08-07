package com.szpejsoft.mydiet.dagger.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.szpejsoft.mydiet.dagger.common.ViewModelFactory
import com.szpejsoft.mydiet.dagger.common.ViewModelKey
import com.szpejsoft.mydiet.screens.nourishments.NourishmentListViewModel
import com.szpejsoft.mydiet.screens.nourishments.NourishmentViewModel
import com.szpejsoft.mydiet.screens.settings.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NourishmentViewModel::class)
    internal abstract fun bindNourishmentViewModel(nourishmentViewModel: NourishmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    internal abstract fun bindSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NourishmentListViewModel::class)
    internal abstract fun bindNourishmentListViewModel(nourishmentListViewModel: NourishmentListViewModel): ViewModel
}