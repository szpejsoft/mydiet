package com.szpejsoft.mydiet.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.szpejsoft.mydiet.plusAssign
import com.szpejsoft.mydiet.subscribeOnCompObserveOnUIBy
import com.szpejsoft.mydiet.utils.SchedulersFacade
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

open class BaseViewModel(
        application: Application,
        protected val schedulersFacade: SchedulersFacade
) : AndroidViewModel(application) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun Completable.subscribeOnCompObserveOnUIBy(
            onError: (Throwable) -> Unit = Timber::w,
            onComplete: () -> Unit = {}
    ): Disposable {
        val subscribe = this.subscribeOnCompObserveOnUIBy(schedulersFacade, onError, onComplete)
        compositeDisposable += subscribe
        return subscribe
    }

    fun <T> Single<T>.subscribeOnCompObserveOnUIBy(
            onError: (Throwable) -> Unit = Timber::w,
            onSuccess: (T) -> Unit = {}
    ): Disposable {
        val subscribe = this.subscribeOnCompObserveOnUIBy(schedulersFacade, onError, onSuccess)
        compositeDisposable += subscribe
        return subscribe
    }

    fun <T> Observable<T>.subscribeOnCompObserveOnUIBy(
            onError: (Throwable) -> Unit = Timber::w,
            onComplete: () -> Unit = {},
            onNext: (T) -> Unit = {}
    ): Disposable {
        val subscribe = this.subscribeOnCompObserveOnUIBy(schedulersFacade, onError, onComplete, onNext)
        compositeDisposable += subscribe
        return subscribe
    }


    fun Disposable.unsubscribe() {
        compositeDisposable.delete(this)
        if (!this.isDisposed) this.dispose()
    }

}