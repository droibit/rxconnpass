package com.github.droibit.rxconnpass.app.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.preference.PreferenceManager
import com.squareup.leakcanary.RefWatcher
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author kumagai
 */
@Module
class AppModule(private val application: Application, private val refWatcher: RefWatcher) {

    @Singleton
    @Provides
    fun provideAppContext(): Context = application

    @Singleton
    @Provides
    fun provideSharedPreference(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    @Singleton
    @Provides
    fun provideRefWatcher() = refWatcher
}