package com.github.droibit.rxconnpass.app.model.data.api

import com.github.droibit.rxconnpass.app.util.annotation.OpenForTesting
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

    @OpenForTesting
    open class SearchMore : Serializable {
        internal var start = 0
        internal var available = 0
        // リクエストごとのイベント取得数
        internal var count = 0

        val canLoadMore: Boolean
            get() = available > 0 && (start + count) <= available

        fun update(available: Int) {
            this.available = available
            this.start += count
        }

        fun reset() {
            start = 0
            available = 0
            count = 0
        }
    }
}