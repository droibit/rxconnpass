package com.github.droibit.rxconnpass

import com.squareup.moshi.Moshi
import okhttp3.Call
import okhttp3.OkHttpClient
import retrofit2.MoshiConverterFactory
import retrofit2.Retrofit
import retrofit2.RxJavaCallAdapterFactory

private val baseUrl = "http://connpass.com"

/**
 * Created by kumagai on 2016/01/13.
 */
class RxConnpass private constructor(public val service: ConnpassService) {

    companion object {

        @JvmStatic
        fun newConnpass(client: Call.Factory? = null) = newConnpass(baseUrl, client)

        internal fun newConnpass(baseUrl: String, client: Call.Factory? = null): RxConnpass {
            val moshi = Moshi.Builder()
                    .add(ResponseAdapters.factory)
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .callFactory(client ?: OkHttpClient())
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()

            return RxConnpass(retrofit.create(ConnpassService::class.java))
        }
    }
}