package com.szpejsoft.mydiet

import android.app.Application
import com.szpejsoft.mydiet.dagger.component.AppComponent
import com.szpejsoft.mydiet.dagger.component.DaggerAppComponent
import com.szpejsoft.mydiet.dagger.module.AlarmModule
import com.szpejsoft.mydiet.dagger.module.AppModule
import com.szpejsoft.mydiet.dagger.module.DataProvidersModule
import com.szpejsoft.mydiet.dagger.module.SchedulersModule
import com.szpejsoft.mydiet.db.MyDietDatabase
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

class MyDietApplication : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        setupRxErrorHandler()
        appComponent = createAppComponent()
        appComponent.inject(this)
    }

    fun getAppComponent(): AppComponent = appComponent

    private fun createAppComponent(): AppComponent =
            DaggerAppComponent.builder()
                    .appModule(AppModule(this))
                    .schedulersModule(SchedulersModule())
                    .dataProvidersModule(DataProvidersModule(MyDietDatabase.getInstance(this)))
                    .alarmModule(AlarmModule())
                    .build()

    private fun setupRxErrorHandler() {
        RxJavaPlugins.setErrorHandler { e -> Timber.e(e, "Unhandled error") }
    }
}