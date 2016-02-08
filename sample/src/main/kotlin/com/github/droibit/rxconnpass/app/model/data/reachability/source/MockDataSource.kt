package com.github.droibit.rxconnpass.app.model.data.reachability.source

import javax.inject.Inject

/**
 * Created by kumagai on 2016/02/08.
 */
class MockDataSource @Inject constructor(private val connected: Boolean) : DataSource {

    override fun connectedAny() = connected
}