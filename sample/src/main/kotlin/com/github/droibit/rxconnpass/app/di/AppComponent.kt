package com.github.droibit.rxconnpass.app.di

import android.content.Context
import android.content.SharedPreferences
import com.github.droibit.rxconnpass.RxConnpass
import com.github.droibit.rxconnpass.app.RxConnpassApplication
import com.github.droibit.rxconnpass.app.ui.activity.EventListActivity
import com.github.droibit.rxconnpass.app.ui.fragment.dialog.EventSortOrderDialogFragment
import dagger.Component
import javax.inject.Singleton

/**
 * @author kumagai
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, DataModule::class))
interface AppComponent {

    fun inject(application: RxConnpassApplication)

    fun inject(activity: EventListActivity)
    fun inject(fragment: EventSortOrderDialogFragment)

    fun plus(module: EventModule): EventComponent

    fun context(): Context
    fun sharedPreference(): SharedPreferences
    fun rxConnpass(): RxConnpass
}
