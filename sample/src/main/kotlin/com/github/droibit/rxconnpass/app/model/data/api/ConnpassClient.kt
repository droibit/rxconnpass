package com.github.droibit.rxconnpass.app.model.data.api

import com.github.droibit.rxconnpass.app.model.data.api.source.DataSource
import java.io.Serializable
import javax.inject.Inject
import javax.inject.Singleton


/**
 *
 * @author kumagai
 */
@Singleton
class ConnpassClient @Inject constructor(source: DataSource): DataSource by source {

    class SearchMore : Serializable {
        var start = 0
        var available = 0
        // リクエストごとのイベント取得数
        var count = 0

        val canLoadMore: Boolean
            get() = available < 0 && (start + count) < available
    }
}