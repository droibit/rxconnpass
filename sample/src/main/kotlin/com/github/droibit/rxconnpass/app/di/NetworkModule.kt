@file:JvmName("NetworkModules")

package com.github.droibit.rxconnpass.app.di

import android.content.Context
import com.github.droibit.rxconnpass.RxConnpass
import com.github.droibit.rxconnpass.app.BuildConfig
import com.github.droibit.rxconnpass.app.model.data.ConnpassClient
import com.github.droibit.rxconnpass.app.model.data.MockCore
import com.github.droibit.rxconnpass.app.model.data.StandardCore
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/**
 * @author kumagai
 */
@Module
class NetworkModule {

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
    fun provideRxConnpass(okhttp: OkHttpClient) = RxConnpass.newConnpass(okhttp)

    @Singleton
    @Provides
    fun provideConnpassClient(rxConnpass: RxConnpass) = ConnpassClient(core = StandardCore(rxConnpass))
}

/**
 * @author kumagai
 */
@Module
class MockNetworkModule {

    @Singleton
    @Provides
    fun provideConnpassClient(context: Context) = ConnpassClient(core = MockCore(context))
}