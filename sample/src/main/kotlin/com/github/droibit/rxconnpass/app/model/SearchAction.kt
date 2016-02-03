package com.github.droibit.rxconnpass.app.model

import android.support.annotation.CheckResult
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.di.scope.PerEvent
import rx.Observable

/**
 *
 * @author kumagai
 */
@PerEvent
interface SearchAction {

    @CheckResult
    fun search(param: String): Observable<List<Event>>
}