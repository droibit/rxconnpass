package com.github.droibit.rxconnpass.app.di

import com.github.droibit.rxconnpass.app.model.api.core.CoreClient
import dagger.Subcomponent
import javax.inject.Singleton

/**
 *
 * @author kumagai
 */
@Singleton
@Subcomponent(modules = arrayOf(MockConnpassModule::class))
interface MockConnpassComponent {

    fun add(module: ActionModule): ActionComponent

    fun coreClient(): CoreClient
}