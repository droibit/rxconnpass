package com.github.droibit.rxconnpass.app.ui.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author kumagai
 */
@Module
class AppModule(val application: Application) {

    @Singleton
    @Provides
    fun provideAppContext(): Context = application
}