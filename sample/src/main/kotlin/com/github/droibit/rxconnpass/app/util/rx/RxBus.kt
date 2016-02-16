package com.github.droibit.rxconnpass.app.util.rx

import rx.Observable
import rx.subjects.PublishSubject
import rx.subjects.SerializedSubject
import javax.inject.Inject
import javax.inject.Singleton

/**
 * courtesy: https://gist.github.com/benjchristensen/04eef9ca0851f3a5d7bf
 *
 * [RxBus.java](https://github.com/kaushikgopal/RxJava-Android-Samples/blob/master/app/src/main/java/com/morihacky/android/rxjava/rxbus/RxBus.java)
 */
@Singleton
class RxBus @Inject constructor() {

    // If multiple threads are going to emit events to this
    // then it must be made thread-safe like this instead
    private val bus = SerializedSubject(PublishSubject.create<Any>())

    val hasObservers: Boolean
        get() = bus.hasObservers()

    fun send(o: Any) {
        bus.onNext(o)
    }

    fun toObserverable(): Observable<Any> = bus
}