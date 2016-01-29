package com.github.droibit.rxconnpass.app.di

import com.github.droibit.rxconnpass.RxConnpass
import com.github.droibit.rxconnpass.app.BuildConfig
import com.github.droibit.rxconnpass.app.model.api.core.ConnpassCore
import com.github.droibit.rxconnpass.app.model.api.core.CoreClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/**
 *
 *
 * @author kumagai
 */
@Module
class ConnpassModule {

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().run {
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BASIC)
                }
                addInterceptor(interceptor)
            }
            build()
        }
    }

    @Singleton
    @Provides
    fun provideClientCore(okhttp: OkHttpClient): CoreClient = ConnpassCore(RxConnpass.newConnpass(okhttp))
}