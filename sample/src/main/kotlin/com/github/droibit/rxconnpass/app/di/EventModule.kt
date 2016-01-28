package com.github.droibit.rxconnpass.app.di

import com.github.droibit.rxconnpass.app.di.scope.PerActivity
import com.github.droibit.rxconnpass.app.model.SearchAction
import com.github.droibit.rxconnpass.app.model.data.ConnpassClient
import dagger.Module
import dagger.Provides
import rx.subscriptions.CompositeSubscription

/**
 *
 *
 * @author kumagai
 */
@Module
class EventModule {

    @PerActivity
    @Provides
    fun provideSearchAction(client: ConnpassClient) = SearchAction(client)

    @PerActivity
    @Provides
    fun provideCompositeSubscription() = CompositeSubscription()
}