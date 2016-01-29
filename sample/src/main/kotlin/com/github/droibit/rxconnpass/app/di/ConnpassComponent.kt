package com.github.droibit.rxconnpass.app.di

import com.github.droibit.rxconnpass.app.model.api.core.CoreClient
import dagger.Subcomponent
import javax.inject.Singleton

/**
 * FIXME: モックと通常版ネットワークモジュールの切り替え
 *
 * @author kumagai
 */
@Singleton
@Subcomponent(modules = arrayOf(ConnpassModule::class))
interface ConnpassComponent {

    //fun add(module: ActionModule): ActionComponent

    //fun coreClient(): CoreClient
}