package com.github.droibit.rxconnpass.app.model.api.core

import com.github.droibit.rxconnpass.EventResponse
import com.github.droibit.rxconnpass.RxConnpass
import com.github.droibit.rxconnpass.app.model.api.ConnpassClient
import rx.Observable
import javax.inject.Singleton

@Singleton
class ConnpassCore(rxConnpass: RxConnpass): CoreClient {

    private val service = rxConnpass.service

    override fun searchByKeyword(keyword: String, searchMore: ConnpassClient.More?): Observable<EventResponse> {
        throw UnsupportedOperationException()
    }
}