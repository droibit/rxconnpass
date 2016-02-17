package com.github.droibit.rxconnpass.app

import android.app.Application
import com.github.droibit.rxconnpass.app.di.AppComponent
import com.github.droibit.rxconnpass.app.di.AppModule
import com.github.droibit.rxconnpass.app.di.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

/**
 *
 *
 * @author kumagai
 */
class RxConnpassApplication : Application() {

    companion object {
        @JvmStatic
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        val refWatcher = LeakCanary.install(this)

        component = DaggerAppComponent.builder()
                .appModule(AppModule(this, refWatcher))
                .build()
    }
}