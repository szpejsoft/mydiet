package com.szpejsoft.mydiet.dagger.component

import android.arch.lifecycle.ViewModelProvider
import com.szpejsoft.mydiet.MyDietActivity
import com.szpejsoft.mydiet.MyDietApplication
import com.szpejsoft.mydiet.MyDietFragment
import com.szpejsoft.mydiet.dagger.module.AppModule
import com.szpejsoft.mydiet.dagger.module.SchedulersModule
import com.szpejsoft.mydiet.dagger.module.ViewModelModule
import com.szpejsoft.mydiet.views.nourishment.NourishmentFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (ViewModelModule::class),
    (AppModule::class),
    (SchedulersModule::class)])
interface AppComponent {

    fun viewModelFactory(): ViewModelProvider.Factory

    fun inject(application: MyDietApplication)

    fun inject(fragment: MyDietFragment)

    fun inject(fragment: NourishmentFragment)

    fun inject(activity: MyDietActivity)
}