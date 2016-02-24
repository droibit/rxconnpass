package com.github.droibit.rxconnpass.app.model.data.api.source

import com.github.droibit.rxconnpass.EventResponse
import com.github.droibit.rxconnpass.Order
import com.github.droibit.rxconnpass.RxConnpass
import com.github.droibit.rxconnpass.app.model.data.api.ConnpassClient
import com.github.droibit.rxconnpass.searchByKeyword
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CloudDataSource @Inject constructor(private val rxConnpass: RxConnpass): DataSource {

    override fun getByKeyword(keyword: String, order: Order, moreSearch: ConnpassClient.MoreSearch): Observable<EventResponse> {
        return rxConnpass.service.searchByKeyword(keyword,
                                                  order = order,
                                                  start = moreSearch.start,
                                                  count = moreSearch.count)
    }
}