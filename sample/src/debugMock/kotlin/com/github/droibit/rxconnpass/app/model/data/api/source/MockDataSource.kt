package com.github.droibit.rxconnpass.app.model.data.api.source

import android.content.Context
import android.content.res.AssetManager
import com.github.droibit.rxconnpass.EventResponse
import com.github.droibit.rxconnpass.Order
import com.github.droibit.rxconnpass.ResponseAdapters
import com.github.droibit.rxconnpass.app.model.data.api.ConnpassClient
import com.github.droibit.rxconnpass.app.util.extension.readText
import com.github.droibit.rxconnpass.emptyEventResponse
import com.squareup.moshi.Moshi
import rx.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockDataSource @Inject constructor(private val context: Context): DataSource {

    private val mockResponse: EventResponse by lazy { readMockResponse(context.assets) }

    override fun getByKeyword(keyword: String, order: Order,searchMore: ConnpassClient.SearchMore): Observable<EventResponse> {
        // TODO: searchMoreによって返すEvent数を変える
        if (keyword.equals("empty")) {
            return Observable.just(emptyEventResponse)
        }
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
