package com.github.droibit.rxconnpass.app.model.data.reachability

import com.github.droibit.rxconnpass.app.model.data.reachability.source.DataSource
import javax.inject.Inject

/**
 * Created by kumagai on 2016/02/08.
 */
class Reachability @Inject constructor(dataSource: DataSource) : DataSource by dataSource {
}