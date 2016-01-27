package com.github.droibit.rxconnpass.app

import android.app.Application
import com.github.droibit.rxconnpass.RxConnpass
import com.github.droibit.rxconnpass.app.model.data.ConnpassClient
import com.github.droibit.rxconnpass.app.model.data.MockClient
import com.github.droibit.rxconnpass.app.ui.di.AppComponent
import com.github.droibit.rxconnpass.app.ui.di.AppModule
import com.github.droibit.rxconnpass.app.ui.di.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import timber.log.Timber

/**
 *
 *
 * @author kumagai
 */
class RxConnpassApplication: Application() {

    companion object {
        @JvmStatic
        lateinit var component: AppComponent
    }

    //var connpassClient: ConnpassClient by lazy { makeConnpass() }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        LeakCanary.install(this)

        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    private fun makeConnpass(): ConnpassClient {
        val okhttp = OkHttpClient.Builder().run {
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor().apply { setLevel(Level.BASIC) }
                addInterceptor(interceptor)
            }
            build()
        }
        val rxConnpass = RxConnpass.newConnpass(okhttp)
        return ConnpassClient(delegate = MockClient(this, rxConnpass))
    }
}