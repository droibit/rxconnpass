package com.droibit.github.rxconnpass.app.model.data

import android.content.Context
import android.content.res.AssetManager
import com.droibit.github.rxconnpass.BuildConfig
import com.droibit.github.rxconnpass.EventResponse
import com.droibit.github.rxconnpass.ResponseAdapters
import com.droibit.github.rxconnpass.RxConnpass
import com.droibit.github.rxconnpass.app.extension.readText
import com.squareup.moshi.Moshi
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
class ConnpassClient(delegate: CoreClient): CoreClient by delegate {

    internal class Current {
        var start = 0
        var available = 0
    }

}

/**
 *
 */
internal class StandardClient(private val context: Context, rxConnpass: RxConnpass): CoreClient {

    private val service = rxConnpass.service
    private var current: ConnpassClient.Current? = null

    override fun searchByKeyword(keyword: String, searchMore: Boolean): Observable<EventResponse> {
        throw UnsupportedOperationException()
    }
}

/**
 *
 */
internal class MockClient(val context: Context, rxConnpass: RxConnpass): CoreClient {

    private val service = rxConnpass.service
    private val mockResponse: EventResponse by lazy { readMockResponse(context.assets) }

    private var current: ConnpassClient.Current? = null

    override fun searchByKeyword(keyword: String, searchMore: Boolean): Observable<EventResponse> {
        // TODO: searchMoreによって返すEvent数を変える
        return Observable.just(mockResponse)
    }

    private fun readMockResponse(assets: AssetManager): EventResponse {
        val json = assets.open("response_15events.json").readText()
        val moshi = Moshi.Builder()
                         .add(ResponseAdapters.factory)
                         .build()
        return moshi.adapter(EventResponse::class.java).fromJson(json)
    }
}
