package com.github.droibit.rxconnpass.app.ui.di

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
}
