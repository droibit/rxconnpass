package com.github.droibit.rxconnpass.app.model.data.reachability.source

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by kumagai on 2016/02/08.
 */
@Singleton
class NetworkDataSource @Inject constructor(context: Context) : DataSource {

    private val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun connectedAny(): Boolean {
        var info = manager.activeNetworkInfo
        return info != null && info.isConnected
    }
}
