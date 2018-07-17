package com.szpejsoft.mydiet

import android.support.v7.app.AppCompatActivity
import com.szpejsoft.mydiet.dagger.component.AppComponent

abstract class MyDietActivity : AppCompatActivity() {
    fun getMyDietApplication(): MyDietApplication = application as MyDietApplication
    fun getAppComponent(): AppComponent = getMyDietApplication().getAppComponent()
}