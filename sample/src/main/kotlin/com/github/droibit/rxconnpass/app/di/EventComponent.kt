package com.github.droibit.rxconnpass.app.di

import com.github.droibit.rxconnpass.app.di.scope.PerActivity
import com.github.droibit.rxconnpass.app.ui.fragment.EventListFragment
import dagger.Component

/**
 * FIXME: モックと通常版ネットワークモジュールの切り替え
 *
 * @author kumagai
 */
@PerActivity
@Component(
    dependencies = arrayOf(AppComponent::class),
    modules = arrayOf(EventModule::class, MockNetworkModule::class)
)
interface EventComponent {

    fun inject(fragment: EventListFragment)
}