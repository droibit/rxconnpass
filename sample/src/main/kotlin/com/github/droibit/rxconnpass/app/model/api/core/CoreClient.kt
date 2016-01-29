package com.github.droibit.rxconnpass.app.model.api.core

import com.github.droibit.rxconnpass.EventResponse
import com.github.droibit.rxconnpass.app.model.api.ConnpassClient
import rx.Observable
import javax.inject.Singleton

/**
 *
 *
 * @author kumagai
 */
@Singleton
interface CoreClient {
    fun searchByKeyword(keyword: String, searchMore: ConnpassClient.More? = null): Observable<EventResponse>
}