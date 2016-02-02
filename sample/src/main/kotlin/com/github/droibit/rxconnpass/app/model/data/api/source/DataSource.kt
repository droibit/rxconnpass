package com.github.droibit.rxconnpass.app.model.data.api.source

import com.github.droibit.rxconnpass.EventResponse
import com.github.droibit.rxconnpass.app.model.data.api.ConnpassClient
import rx.Observable
import javax.inject.Singleton

/**
 *
 *
 * @author kumagai
 */
interface DataSource {

    fun getByKeyword(keyword: String, searchMore: ConnpassClient.More? = null): Observable<EventResponse>
}