package com.github.droibit.rxconnpass.app.model.api.datasource

import com.github.droibit.rxconnpass.EventResponse
import com.github.droibit.rxconnpass.RxConnpass
import com.github.droibit.rxconnpass.app.model.api.ConnpassClient
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnpassDataSource(private val rxConnpass: RxConnpass): DataSource {

    override fun getByKeyword(keyword: String, searchMore: ConnpassClient.More?): Observable<EventResponse> {
        throw UnsupportedOperationException()
    }
}