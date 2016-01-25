package com.github.droibit.rxconnpass.internal

import java.text.SimpleDateFormat
import java.util.*

internal const val DEFAULT_RETURN_COUNT = 10

internal object QueryNames {
    internal const val eventId = "event_id"
    internal const val keyword = "keyword"
    internal const val keywordOr = "keyword_or"
    internal const val ym = "ym"
    internal const val ymd = "ymd"
    internal const val nickname = "nickname"
    internal const val onwerNickname = "owner_nickname"
    internal const val seriesId = "series_id"
    internal const val start = "start"
    internal const val order = "order"
    internal const val count = "count"
}

private val ymFormat by lazy { SimpleDateFormat("yyyyMM") }
private val ymdFormat by lazy { SimpleDateFormat("yyyyMMdd") }

internal fun List<Date>.toYmdDateString(): List<String>? {
    return if (isNotEmpty()) {
        map { ymdFormat.format(it) }
    } else {
        null
    }
}

internal fun List<Date>.toYmDateString(): List<String>? {
    return if (isNotEmpty()) {
        map { ymFormat.format(it) }
    } else {
        null
    }
}