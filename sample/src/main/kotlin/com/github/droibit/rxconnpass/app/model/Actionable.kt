package com.github.droibit.rxconnpass.app.model

import rx.Observable

/**
 *
 *
 * @author kumagai
 */
interface Acitionable<T> {

    fun action(): Observable<T>
}