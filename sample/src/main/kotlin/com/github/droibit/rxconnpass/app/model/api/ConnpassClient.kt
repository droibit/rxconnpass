package com.github.droibit.rxconnpass.app.model.api

import com.github.droibit.rxconnpass.app.model.api.core.CoreClient
import java.io.Serializable
import javax.inject.Inject
import javax.inject.Singleton


/**
 *
 * @author kumagai
 */
@Singleton
class ConnpassClient @Inject constructor(core: CoreClient): CoreClient by core {

    class More: Serializable {
        var start = 0
        var available = 0
    }
}