package com.github.droibit.rxconnpass.app.di

import com.github.droibit.rxconnpass.app.di.scope.PerEvent
import com.github.droibit.rxconnpass.app.ui.fragment.EventListFragment
import dagger.Subcomponent

/**
 * Created by kumagai on 2016/01/29.
 */
@PerEvent
@Subcomponent(modules = arrayOf(EventModule::class))
interface EventComponent {

    fun inject(fragment: EventListFragment)
}