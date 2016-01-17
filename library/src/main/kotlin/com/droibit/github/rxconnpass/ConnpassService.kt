package com.droibit.github.rxconnpass

import com.droibit.github.rxconnpass.internal.GetQuery
import retrofit2.http.GET
import retrofit2.http.QueryMap
import rx.Observable

/**
 * [API Reference](http://connpass.com/about/api/)
 */
public interface ConnpassService {


    @GET("/api/v1/event/")
    fun search(@QueryMap query: Map<String, String>): Observable<EventResponse>
}

enum class Order(val index: Int) {
    UPDATED(0), // 更新日時順
    EVENT(1),   // 開催日時順
    NEW(2),     // 新着順
}

public fun ConnpassService.searchByKeyword(keyword: String,
                                           date: String? = null,
                                           order: Order = Order.UPDATED,
                                           start: Int = 0,
                                           count: Int = 10): Observable<EventResponse> {
    // TODO: dateパース
    return search(hashMapOf(
            GetQuery.keyword to keyword,
            GetQuery.order to order.index.toString(),
            GetQuery.start to start.toString(),
            GetQuery.count to count.toString()
    ))
}