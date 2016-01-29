package com.github.droibit.rxconnpass.app.di

import android.content.Context
import com.github.droibit.rxconnpass.app.RxConnpassApplication
import dagger.Component
import javax.inject.Singleton

/**
 * @author kumagai
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(application: RxConnpassApplication)

    fun add(module: MockConnpassModule): MockConnpassComponent
    //fun add(module: ConnpassModule): ConnpassComponent

    fun context(): Context
}
