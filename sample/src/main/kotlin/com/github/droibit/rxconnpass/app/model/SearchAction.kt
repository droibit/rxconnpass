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

    val searchMore: ConnpassClient.SearchMore?

    @CheckResult
    fun search(param: String): Observable<List<Event>>
}