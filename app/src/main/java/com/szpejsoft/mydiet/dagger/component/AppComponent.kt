package com.szpejsoft.mydiet.dagger.component

import android.arch.lifecycle.ViewModelProvider
import com.szpejsoft.mydiet.MyDietActivity
import com.szpejsoft.mydiet.MyDietApplication
import com.szpejsoft.mydiet.MyDietFragment
import com.szpejsoft.mydiet.dagger.module.AppModule
import com.szpejsoft.mydiet.dagger.module.RepositoryModule
import com.szpejsoft.mydiet.dagger.module.SchedulersModule
import com.szpejsoft.mydiet.dagger.module.ViewModelModule
import com.szpejsoft.mydiet.views.measurements.MeasurementsFragment
import com.szpejsoft.mydiet.views.nourishment.NourishmentFragment
import com.szpejsoft.mydiet.views.settings.SettingsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (ViewModelModule::class),
    (RepositoryModule::class),
    (AppModule::class),
    (SchedulersModule::class)])
interface AppComponent {

    fun viewModelFactory(): ViewModelProvider.Factory

    fun inject(application: MyDietApplication)

    fun inject(activity: MyDietActivity)

    fun inject(fragment: MyDietFragment)

    fun inject(fragment: NourishmentFragment)

    fun inject(fragment: SettingsFragment)

    fun inject(fragment: MeasurementsFragment)
}