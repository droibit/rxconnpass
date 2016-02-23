package com.github.droibit.rxconnpass.app.di

import android.content.Context
import com.github.droibit.rxconnpass.app.model.data.api.source.CloudDataSource
import com.github.droibit.rxconnpass.app.model.data.reachability.source.NetworkDataSource
import com.github.droibit.rxconnpass.app.model.data.api.source.DataSource as ApiDataSource
import com.github.droibit.rxconnpass.app.model.data.settings.source.PreferenceDataSource
import com.github.droibit.rxconnpass.app.model.data.settings.source.DataSource as SettingsDataSource
import com.github.droibit.rxconnpass.app.model.data.reachability.source.DataSource as ReachabilityDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 *
 * @author kumagai
 */
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideApiDataSource(dataSource: CloudDataSource): ApiDataSource = dataSource

    @Singleton
    @Provides
    fun provideSettingsDataSource(dataSource: PreferenceDataSource): SettingsDataSource = dataSource

    @Singleton
    @Provides
    fun provideReachabilityDataSource(context: Context): ReachabilityDataSource = NetworkDataSource(context)
}