package com.jamitlabs.starfacepresentation.util.rxjava

import io.reactivex.*


interface SchedulerProvider {
    fun io() : Scheduler
    fun ui() : Scheduler
    fun computation() : Scheduler
}

fun Completable.with(schedulerProvider: SchedulerProvider): Completable =
        this.observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())

fun <T> Single<T>.with(schedulerProvider: SchedulerProvider): Single<T> =
        this.observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())

fun <T> Observable<T>.with(schedulerProvider: SchedulerProvider): Observable<T> =
        this.observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())

fun <T> Flowable<T>.with(schedulerProvider: SchedulerProvider): Flowable<T> =
        this.observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())

fun <T> Maybe<T>.with(schedulerProvider: SchedulerProvider): Maybe<T> =
        this.observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())

