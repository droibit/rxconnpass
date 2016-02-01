package com.github.droibit.rxconnpass.app.di

import android.content.Context
import com.github.droibit.rxconnpass.RxConnpass
import com.github.droibit.rxconnpass.app.RxConnpassApplication
import dagger.Component
import javax.inject.Singleton

/**
 * @author kumagai
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, ConnpassModule::class))
interface AppComponent {

    fun inject(application: RxConnpassApplication)

    fun plus(module: EventModule): EventComponent

    fun context(): Context
    fun rxConnpass(): RxConnpass
}