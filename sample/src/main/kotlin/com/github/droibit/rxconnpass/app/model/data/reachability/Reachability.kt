package com.github.droibit.rxconnpass.app.model.data.reachability

import com.github.droibit.rxconnpass.app.model.data.reachability.source.DataSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by kumagai on 2016/02/08.
 */
@Singleton
class Reachability @Inject constructor(source: DataSource) : DataSource by source