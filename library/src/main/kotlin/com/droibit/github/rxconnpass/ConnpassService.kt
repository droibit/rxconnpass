package com.droibit.github.rxconnpass

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * [API Reference](http://connpass.com/about/api/)
 */
public interface ConnpassService {

    @GET("/api/v1/event/")
    fun search(@Query("keyword") keyword: String): Observable<EventResponse>
}