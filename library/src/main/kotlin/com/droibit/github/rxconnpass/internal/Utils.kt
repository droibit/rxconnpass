package com.droibit.github.rxconnpass.internal

import java.text.SimpleDateFormat
import java.util.*

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