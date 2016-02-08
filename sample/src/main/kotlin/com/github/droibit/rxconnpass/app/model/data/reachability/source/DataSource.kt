package com.github.droibit.rxconnpass.app.model.data.reachability.source

/**
 * Created by kumagai on 2016/02/08.
 */
interface DataSource {

    fun connectedAny(): Boolean
}