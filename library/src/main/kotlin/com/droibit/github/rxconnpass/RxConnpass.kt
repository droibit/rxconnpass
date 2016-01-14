package com.droibit.github.rxconnpass

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

        fun newConnpass(client: Call.Factory? = null): RxConnpass {
            val retrofit = Retrofit.Builder().run {
                baseUrl(baseUrl)
                addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                callFactory(client ?: OkHttpClient())

                val moshi = Moshi.Builder().run {
                    add(ResponseAdapters.factory)
                    build()
                }
                addConverterFactory(MoshiConverterFactory.create(moshi))

                build()
            }
            return RxConnpass(retrofit.create(ConnpassService::class.java))
        }
    }
}