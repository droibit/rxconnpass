package com.github.droibit.rxconnpass.app.di

import com.github.droibit.rxconnpass.app.model.data.api.source.DataSource as ApiDataSource
import com.github.droibit.rxconnpass.app.model.data.settings.source.DataSource as SettingsDataSource
import com.github.droibit.rxconnpass.app.model.data.api.source.MockDataSource
import com.github.droibit.rxconnpass.app.model.data.settings.source.PreferenceDataSource
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
    fun provideApiDataSource(dataSource: MockDataSource): ApiDataSource = dataSource

    @Singleton
    @Provides
    fun provideSettingsDataSource(dataSource: PreferenceDataSource): SettingsDataSource = dataSource
}