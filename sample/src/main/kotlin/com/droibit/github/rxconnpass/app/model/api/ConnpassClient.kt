package com.droibit.github.rxconnpass.app.model.api

import android.content.Context
import com.droibit.github.rxconnpass.EventResponse
import com.droibit.github.rxconnpass.RxConnpass
import rx.Observable

/**
 *
 *
 * @author kumagai
 */
interface CoreClient {

    fun searchByKeyword(keyword: String, searchMore: Boolean = false): Observable<EventResponse>
}

/**
 *
 *
 */
class ConnpassClient(delegate: CoreClient): CoreClient by delegate

/**
 *
 */
internal class StandardClient(private val context: Context, rxConnpass: RxConnpass): CoreClient {

    internal class Current {
        var start = 0
        var available = 0
    }

    private val service = rxConnpass.service
    private var current: Current? = null

    override fun searchByKeyword(keyword: String, searchMore: Boolean): Observable<EventResponse> {
        throw UnsupportedOperationException()
    }
}

/**
 *
 */
internal class MockClient(private val context: Context, rxConnpass: RxConnpass): CoreClient {

    private val service = rxConnpass.service

    override fun searchByKeyword(keyword: String, searchMore: Boolean): Observable<EventResponse> {
        throw UnsupportedOperationException()
    }
}
