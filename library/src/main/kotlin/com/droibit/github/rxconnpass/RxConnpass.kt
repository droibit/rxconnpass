package com.droibit.github.rxconnpass

import com.droibit.github.rxconnpass.internal.ResponseAdapters
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
public class RxConnpass private constructor(public val service: ConnpassService) {

    companion object {

        @JvmStatic
        public fun newClient(client: Call.Factory? = null) = newClient(baseUrl, client)

        internal fun newClient(baseUrl: String, client: Call.Factory? = null): RxConnpass {
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