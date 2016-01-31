package com.github.droibit.rxconnpass.app.model.api.datasource

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
interface DataSource {

    fun getByKeyword(keyword: String, searchMore: ConnpassClient.More? = null): Observable<EventResponse>
}