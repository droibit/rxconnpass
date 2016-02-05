package com.github.droibit.rxconnpass.app.model.data.settings

import com.github.droibit.rxconnpass.app.model.data.settings.source.DataSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by kumagai on 2016/02/01.
 */
@Singleton
class Settings @Inject constructor(dataSource: DataSource) : DataSource by dataSource