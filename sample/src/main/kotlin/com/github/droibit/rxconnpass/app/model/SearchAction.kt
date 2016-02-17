package com.github.droibit.rxconnpass.app.model

import android.support.annotation.CheckResult
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.di.scope.PerEvent
import com.github.droibit.rxconnpass.app.model.data.api.ConnpassClient
import rx.Observable

/**
 *
 * @author kumagai
 */
@PerEvent
interface SearchAction {

    val canLoadMore: Boolean

    @CheckResult
    fun search(param: String): Observable<List<Event>>

    @CheckResult
    fun research(): Observable<List<Event>>

    @CheckResult
    fun searchMore(): Observable<List<Event>>
}