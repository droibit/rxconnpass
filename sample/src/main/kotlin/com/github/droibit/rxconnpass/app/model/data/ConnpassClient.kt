package com.github.droibit.rxconnpass.app.model.data

import android.content.Context
import android.content.res.AssetManager
import com.github.droibit.rxconnpass.EventResponse
import com.github.droibit.rxconnpass.ResponseAdapters
import com.github.droibit.rxconnpass.RxConnpass
import com.github.droibit.rxconnpass.app.util.extension.readText
import com.squareup.moshi.Moshi
import rx.Observable
import java.io.Serializable

/**
 *
 *
 * @author kumagai
 */
interface CoreClient {

    fun searchByKeyword(keyword: String, searchMore: ConnpassClient.More? = null): Observable<EventResponse>
}

/**
 *
 *
 */
class ConnpassClient(delegate: CoreClient): CoreClient by delegate {

    class More: Serializable {
        var start = 0
        var available = 0
    }

}

/**
 *
 */
internal class StandardClient(private val context: Context, rxConnpass: RxConnpass): CoreClient {

    private val service = rxConnpass.service

    override fun searchByKeyword(keyword: String, searchMore: ConnpassClient.More?): Observable<EventResponse> {
        throw UnsupportedOperationException()
    }
}

/**
 *
 */
internal class MockClient(val context: Context, rxConnpass: RxConnpass): CoreClient {

    private val mockResponse: EventResponse by lazy { readMockResponse(context.assets) }

    override fun searchByKeyword(keyword: String, searchMore: ConnpassClient.More?): Observable<EventResponse> {
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
