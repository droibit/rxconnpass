package com.github.droibit.rxconnpass.app.model.data.reachability.source

import javax.inject.Singleton

/**
 * Created by kumagai on 2016/02/08.
 */
@Singleton
class MockNetworkDataSource(private val connected: Boolean) : DataSource {

    override fun connectedAny() = connected
}