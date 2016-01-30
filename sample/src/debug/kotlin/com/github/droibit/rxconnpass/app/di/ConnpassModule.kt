package com.github.droibit.rxconnpass.app.di

import com.github.droibit.rxconnpass.RxConnpass
import com.github.droibit.rxconnpass.app.model.api.core.ConnpassCore
import com.github.droibit.rxconnpass.app.model.api.core.CoreClient
import dagger.Module
import dagger.Provides
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
    fun provideCoreClient(rxConnpass: RxConnpass): CoreClient = ConnpassCore(rxConnpass)
}