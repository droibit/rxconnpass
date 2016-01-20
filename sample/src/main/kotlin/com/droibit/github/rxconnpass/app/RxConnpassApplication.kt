package com.droibit.github.rxconnpass.app

import android.app.Application
import com.droibit.github.rxconnpass.RxConnpass
import com.droibit.github.rxconnpass.app.model.api.ConnpassClient
import com.droibit.github.rxconnpass.app.model.api.StandardClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level

/**
 *
 *
 * @author kumagai
 */
class RxConnpassApplication: Application() {

    val connpassClient: ConnpassClient
        get() = _connpassClient
    private lateinit var _connpassClient: ConnpassClient

    override fun onCreate() {
        super.onCreate()

        val okhttp = OkHttpClient.Builder().run {
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor().apply { setLevel(Level.BASIC) }
                addInterceptor(interceptor)
            }
            build()
        }
        val rxConnpass = RxConnpass.newConnpass(okhttp)
        _connpassClient = ConnpassClient(delegate = StandardClient(this, rxConnpass))
    }
}