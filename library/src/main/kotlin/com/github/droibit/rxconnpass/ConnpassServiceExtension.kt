@file:JvmName("ConnpassServiceUtil")
package com.github.droibit.rxconnpass

import com.github.droibit.rxconnpass.internal.DEFAULT_RETURN_COUNT
import com.github.droibit.rxconnpass.internal.toYmDateStrings
import com.github.droibit.rxconnpass.internal.toYmdDateStrings
import rx.Observable
import java.util.*

/**
 * TODO: 日付のことを明示する
 */
fun ConnpassService.searchByKeyword(keyword: String,
                                    ymdDates: List<Date>? = null,
                                    ymDates: List<Date>? = null,
                                    order: Order = Order.UPDATED,
                                    start: Int = 0,
                                    count: Int = DEFAULT_RETURN_COUNT): Observable<EventResponse> {
    return searchByKeyword(keyword,
                           ymdDates?.toYmdDateStrings(),
                           ymDates?.toYmDateStrings(),
                           order, start, count)
}