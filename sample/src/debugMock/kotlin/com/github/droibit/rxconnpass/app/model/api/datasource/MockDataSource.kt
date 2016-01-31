package com.github.droibit.rxconnpass.app.model.api.datasource

import android.content.Context
import android.content.res.AssetManager
import com.github.droibit.rxconnpass.EventResponse
import com.github.droibit.rxconnpass.ResponseAdapters
import com.github.droibit.rxconnpass.app.model.api.ConnpassClient
import com.github.droibit.rxconnpass.app.util.extension.readText
import com.squareup.moshi.Moshi
import rx.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
class MockDataSource(private val context: Context): DataSource {

    private val mockResponse: EventResponse by lazy { readMockResponse(context.assets) }

    override fun getByKeyword(keyword: String, searchMore: ConnpassClient.More?): Observable<EventResponse> {
        // TODO: searchMoreによって返すEvent数を変える
        return Observable.just(mockResponse)
                .delay(3, TimeUnit.SECONDS)
    }

    private fun readMockResponse(assets: AssetManager): EventResponse {
        val json = assets.open("response_15events.json").readText()
        val moshi = Moshi.Builder()
                .add(ResponseAdapters.factory)
                .build()
        return moshi.adapter(EventResponse::class.java).fromJson(json)
    }
}
