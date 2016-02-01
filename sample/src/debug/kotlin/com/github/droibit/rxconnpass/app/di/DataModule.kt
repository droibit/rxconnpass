package com.github.droibit.rxconnpass.app.di

import com.github.droibit.rxconnpass.app.model.api.datasource.ConnpassDataSource
import com.github.droibit.rxconnpass.app.model.settings.datasource.PreferenceDataSource
import com.github.droibit.rxconnpass.app.model.api.datasource.DataSource as ApiDataSource
import com.github.droibit.rxconnpass.app.model.settings.datasource.DataSource as SettingsDataSource
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
    fun provideApiDataSource(dataSource: ConnpassDataSource): ApiDataSource = dataSource

    @Singleton
    @Provides
    fun provideSettingsDataSource(dataSource: PreferenceDataSource): SettingsDataSource = dataSource
}