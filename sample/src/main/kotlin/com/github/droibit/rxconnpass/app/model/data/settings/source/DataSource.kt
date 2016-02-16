package com.github.droibit.rxconnpass.app.model.data.settings.source

import com.github.droibit.rxconnpass.Order

/**
 * Created by kumagai on 2016/02/01.
 */
interface DataSource {

    var countPerRequest: Int
    var eventOrder: Int
}

internal fun Int.toOrder() = Order.values().filter { this@toOrder == it.index }.first()