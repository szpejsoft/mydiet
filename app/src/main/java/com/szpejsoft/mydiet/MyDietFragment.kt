package com.szpejsoft.mydiet


import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import com.szpejsoft.mydiet.dagger.component.AppComponent
import javax.inject.Inject

abstract class MyDietFragment : Fragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppComponent().inject(this)
    }

    fun getMyDietActivity(): MyDietActivity = activity as MyDietActivity
    fun getMyDietApplicaton(): MyDietApplication = getMyDietActivity().getMyDietApplication()
    fun getAppComponent(): AppComponent = getMyDietActivity().getAppComponent()

}