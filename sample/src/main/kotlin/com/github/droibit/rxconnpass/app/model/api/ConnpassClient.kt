package com.github.droibit.rxconnpass.app.model.api

import com.github.droibit.rxconnpass.app.model.api.datasource.DataSource
import java.io.Serializable
import javax.inject.Inject
import javax.inject.Singleton


/**
 *
 * @author kumagai
 */
@Singleton
class ConnpassClient @Inject constructor(source: DataSource): DataSource by source {

    class More: Serializable {
        var start = 0
        var available = 0
    }
}