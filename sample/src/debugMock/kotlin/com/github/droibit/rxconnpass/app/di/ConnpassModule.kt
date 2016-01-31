package com.github.droibit.rxconnpass.app.di

import android.content.Context
import com.github.droibit.rxconnpass.RxConnpass
import com.github.droibit.rxconnpass.app.model.api.datasource.ConnpassDataSource
import com.github.droibit.rxconnpass.app.model.api.datasource.DataSource
import com.github.droibit.rxconnpass.app.model.api.datasource.MockDataSource
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
    fun provideCoreClient(context: Context): DataSource = MockDataSource(context)
}