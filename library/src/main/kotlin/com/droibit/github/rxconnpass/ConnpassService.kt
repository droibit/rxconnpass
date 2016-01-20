package com.droibit.github.rxconnpass

import com.droibit.github.rxconnpass.internal.DEFAULT_RETURN_COUNT
import com.droibit.github.rxconnpass.internal.QueryNames
import com.droibit.github.rxconnpass.internal.toYmDateString
import com.droibit.github.rxconnpass.internal.toYmdDateString
import retrofit2.http.GET
import retrofit2.http.QueryMap
import rx.Observable
import java.util.*

/**
 * [API Reference](http://connpass.com/about/api/)
 */
interface ConnpassService {

    @GET("/api/v1/event/")
    fun search(@QueryMap query: Map<String, @JvmSuppressWildcards Any?>): Observable<EventResponse>
}

enum class Order(val index: Int) {
    UPDATED(0), // 更新日時順
    EVENT(1),   // 開催日時順
    NEW(2),     // 新着順
}

/**
 * TODO: 日付のことを明示する
 */
fun ConnpassService.searchByKeyword(keyword: String,
                                    ymdDates: List<Date>? = null,
                                    ymDates: List<Date>? = null,
                                    order: Order = Order.UPDATED,
                                    start: Int = 0,
                                    count: Int = DEFAULT_RETURN_COUNT): Observable<EventResponse> {
    return search(hashMapOf(
            QueryNames.keyword to keyword,
            QueryNames.ymd to ymdDates?.toYmdDateString(),
            QueryNames.ym to ymDates?.toYmDateString(),
            QueryNames.order to order.index,
            QueryNames.start to start,
            QueryNames.count to count)
    )
}