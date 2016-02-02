package com.github.droibit.rxconnpass.app.model.data.api.source

import android.support.annotation.CheckResult
import com.github.droibit.rxconnpass.EventResponse
import com.github.droibit.rxconnpass.app.model.data.api.ConnpassClient
import rx.Observable
import javax.inject.Singleton

/**
 *
 *
 * @author kumagai
 */
@Singleton
interface DataSource {

    @CheckResult
    fun getByKeyword(keyword: String, searchMore: ConnpassClient.More? = null): Observable<EventResponse>
}