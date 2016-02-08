package com.github.droibit.rxconnpass.app.model.data.reachability.source

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by kumagai on 2016/02/08.
 */
@Singleton
class MockNetworkDataSource @Inject constructor() : DataSource {

    override fun connectedAny() = true
}