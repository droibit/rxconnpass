package com.github.droibit.rxconnpass.app.di

import android.content.Context
import com.github.droibit.rxconnpass.RxConnpass
import com.github.droibit.rxconnpass.app.BuildConfig
import com.github.droibit.rxconnpass.app.model.data.ConnpassClient
import com.github.droibit.rxconnpass.app.model.data.MockClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/**
 * @author kumagai
 */
@Module
class ConnpassModule {

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().run {
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BASIC) }
                addInterceptor(interceptor)
            }
            build()
        }
    }

    @Singleton
    @Provides
    fun provideRxConnpass(okhttp: OkHttpClient) = RxConnpass.newConnpass(okhttp)
}