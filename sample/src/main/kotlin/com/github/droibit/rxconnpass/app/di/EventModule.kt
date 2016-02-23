package com.github.droibit.rxconnpass.app.di

import com.github.droibit.rxconnpass.app.di.scope.PerEvent
import com.github.droibit.rxconnpass.app.model.EventSearchAction
import com.github.droibit.rxconnpass.app.model.SearchAction
import dagger.Module
import dagger.Provides
import rx.subscriptions.CompositeSubscription
import javax.inject.Named

/**
 * Created by kumagai on 2016/01/29.
 */
@Module
class EventModule {

    @Provides
    fun provideCompositeSubscription() = CompositeSubscription()

    @PerEvent
    @Provides
    @Named("searchEvent")
    fun provideEventSearchAction(action: EventSearchAction): SearchAction = action
}