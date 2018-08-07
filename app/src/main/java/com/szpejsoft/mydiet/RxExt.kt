package com.szpejsoft.mydiet

import com.szpejsoft.mydiet.utils.SchedulersFacade
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

operator fun CompositeDisposable.plusAssign(subscribe: Disposable) {
    add(subscribe)
}

fun <T> Single<T>.subscribeOnCompObserveOnUIBy(schedulers: SchedulersFacade,
                                               onError: (Throwable) -> Unit = (Timber::w),
                                               onSuccess: (T) -> Unit
) = subscribeOn(schedulers.computation())
        .observeOn(schedulers.ui())
        .subscribe(onSuccess, onError)

fun <T> Observable<T>.subscribeOnCompObserveOnUIBy(schedulers: SchedulersFacade,
                                                   onError: (Throwable) -> Unit = (Timber::w),
                                                   onComplete: () -> Unit,
                                                   onNext: (T) -> Unit
) = subscribeOn(schedulers.computation())
        .observeOn(schedulers.ui())
        .subscribe(onNext, onError, onComplete)

fun Completable.subscribeOnCompObserveOnUIBy(schedulers: SchedulersFacade,
                                             onError: (Throwable) -> Unit = (Timber::w),
                                             onComplete: () -> Unit = {}
) = subscribeOn(schedulers.computation())
        .observeOn(schedulers.ui())
        .subscribe(onComplete, onError)

fun <T> Single<T>.subscribeOnCompObserveOnCompBy(schedulers: SchedulersFacade,
                                                 onError: (Throwable) -> Unit = (Timber::w),
                                                 onSuccess: (T) -> Unit
) = subscribeOn(schedulers.computation())
        .observeOn(schedulers.computation())
        .subscribe(onSuccess, onError)

fun <T> Observable<T>.subscribeOnCompObserveOnCompBy(schedulers: SchedulersFacade,
                                                     onError: (Throwable) -> Unit = (Timber::w),
                                                     onComplete: () -> Unit,
                                                     onNext: (T) -> Unit
) = subscribeOn(schedulers.computation())
        .observeOn(schedulers.computation())
        .subscribe(onNext, onError, onComplete)

fun Completable.subscribeOnCompObserveOnCompBy(schedulers: SchedulersFacade,
                                               onError: (Throwable) -> Unit = (Timber::w),
                                               onComplete: () -> Unit = {}
) = subscribeOn(schedulers.computation())
        .observeOn(schedulers.computation())
        .subscribe(onComplete, onError)


fun <T> Observable<T>.debugToConsole(id: String) = this
        .doOnSubscribe { println("[$id] onSubscribe => $it") }
        .doOnNext { println("[$id] onNext => $it") }
        .doOnError { println("[$id] onError => $it") }
        .doOnComplete { println("[$id] onComplete") }
        .doOnDispose { println("[$id] onDispose") }

fun <T> Observable<T>.debugToLogcat(id: String) = this
        .doOnSubscribe { Timber.d("[$id] onSubscribe => $it") }
        .doOnNext { Timber.d("[$id] onNext => $it") }
        .doOnError { Timber.d("[$id] onError => $it") }
        .doOnComplete { Timber.d("[$id] onComplete") }
        .doOnDispose {
            Timber.d(Thread.currentThread().stackTrace.contentToString())
            Timber.d("[$id] onDispose")
        }

fun <T> Single<T>.debugToConsole(id: String) = this
        .doOnSubscribe { println("[$id] onSubscribe => $it") }
        .doOnSuccess { println("[$id] onSuccess => $it") }
        .doOnError { println("[$id] onError => $it") }
        .doOnDispose { println("[$id] onDispose") }

fun <T> Single<T>.debugToLogcat(id: String) = this
        .doOnSubscribe { Timber.d("[$id] onSubscribe => $it") }
        .doOnSuccess { Timber.d("[$id] onSuccess => $it") }
        .doOnError { Timber.d("[$id] onError => $it") }
        .doOnDispose { Timber.d("[$id] onDispose") }

fun Completable.debugToLogcat(id: String) = this
        .doOnSubscribe { Timber.d("[$id] onSubscribe => $it") }
        .doOnComplete { Timber.d("[$id] onComplete") }
        .doOnError { Timber.d("[$id] onError => $it") }
        .doOnDispose { Timber.d("[$id] onDispose") }

fun Completable.debugToConsole(id: String) = this
        .doOnSubscribe { println("[$id] onSubscribe => $it") }
        .doOnComplete { println("[$id] onComplete") }
        .doOnError { println("[$id] onError => $it") }
        .doOnDispose { println("[$id] onDispose") }

fun <T> Maybe<T>.debugToLogcat(id: String) = this
        .doOnSubscribe { Timber.d("[$id] onSubscribe => $it") }
        .doOnComplete { Timber.d("[$id] onComplete") }
        .doOnSuccess { Timber.d("[$id] onSuccess => $it") }
        .doOnError { Timber.d("[$id] onError => $it") }
        .doOnDispose { Timber.d("[$id] onDispose") }

fun <T> Maybe<T>.debugToConsole(id: String) = this
        .doOnSubscribe { println("[$id] onSubscribe => $it") }
        .doOnComplete { println("[$id] onComplete") }
        .doOnSuccess { println("[$id] onSuccess") }
        .doOnError { println("[$id] onError => $it") }
        .doOnDispose { println("[$id] onDispose") }