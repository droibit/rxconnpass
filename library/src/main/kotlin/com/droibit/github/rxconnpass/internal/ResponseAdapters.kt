package com.droibit.github.rxconnpass.internal

import com.squareup.moshi.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

internal object ResponseAdapters {

    val factory: JsonAdapter.Factory = JsonAdapter.Factory { type, annotations, moshi ->
        if (type == Date::class.java) {
            // FIXME: 本来はアノテーションのチェックがいる
            dateJsonAdapter
        } else {
            null
        }
    }
}

internal val iso8601Format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
internal val dateJsonAdapter = object: JsonAdapter<Date>() {
    override fun toJson(writer: JsonWriter, value: Date) {}

    override fun fromJson(reader: JsonReader): Date {
        val dateString = reader.nextString()
        try {
            return iso8601Format.parse(dateString)
        } catch (e: ParseException) {
            throw JsonDataException("Unsupported format date: $dateString")
        }
    }

    override fun toString() = "JsonAdapter(Date)"
}
