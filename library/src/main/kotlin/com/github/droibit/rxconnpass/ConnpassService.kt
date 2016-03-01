package com.github.droibit.rxconnpass

import com.github.droibit.rxconnpass.internal.QueryName
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * [API Reference](http://connpass.com/about/api/)
 */
interface ConnpassService {

    @GET("/api/v1/event/")
    fun searchByKeyword(@Query(QueryName.keyword) keyword: String,
                        @Query(QueryName.ym) ymDates: List<@JvmSuppressWildcards String>?,
                        @Query(QueryName.ymd) ymdDates: List<@JvmSuppressWildcards String>?,
                        @Query(QueryName.order) order: Order,
                        @Query(QueryName.start) start: Int,
                        @Query(QueryName.count) count: Int): Observable<EventResponse>
}