package com.github.droibit.rxconnpass.app.di

import dagger.Module
import dagger.Provides
import rx.subscriptions.CompositeSubscription

/**
 * Created by kumagai on 2016/01/29.
 */
@Module
class EventModule {

    @Provides
    fun provideCompositeSubscription() = CompositeSubscription()
}