package com.github.droibit.rxconnpass.app.model.data.api

import com.github.droibit.rxconnpass.app.model.data.api.source.DataSource
import com.github.droibit.rxconnpass.app.util.annotation.OpenForTesting
import java.io.Serializable
import javax.inject.Inject
import javax.inject.Singleton


/**
 *
 * @author kumagai
 */
@Singleton
class ConnpassClient @Inject constructor(source: DataSource): DataSource by source {

    @OpenForTesting
    open class MoreSearch : Serializable {
        var start = 0
        var available = 0
        // リクエストごとのイベント取得数
        var count = 0

        val canLoadMore: Boolean
            get() = available > 0 && (start + count) <= available

        fun update(count: Int) {
            check(count > 0)

            this.count = count
            this.start += count

        }

        fun reset() {
            start = 0
            available = 0
            count = 0
        }
    }
}