package com.github.droibit.rxconnpass.app.di

import android.content.Context
import com.github.droibit.rxconnpass.app.model.api.core.CoreClient
import com.github.droibit.rxconnpass.app.model.api.core.MockConnpassCore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author kumagai
 */
@Module
class MockConnpassModule {

    @Singleton
    @Provides
    fun provideClientCore(context: Context): CoreClient = MockConnpassCore(context)
}