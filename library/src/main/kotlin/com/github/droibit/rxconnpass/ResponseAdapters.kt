package com.github.droibit.rxconnpass

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object ResponseAdapters {

    @JvmField
    val factory: JsonAdapter.Factory = JsonAdapter.Factory { type, annotations, moshi ->
        when (type) {
            Date::class.java -> dateJsonAdapter
            else -> null
        }
    }

    @JvmField
    internal val dateJsonAdapter = object : JsonAdapter<Date>() {
        override fun toJson(writer: JsonWriter, value: Date) {
        }

        override fun fromJson(reader: JsonReader): Date {
            val dateString = reader.nextString()
            return try {
                iso8601Format.parse(dateString)
            } catch (e: ParseException) {
                throw JsonDataException("Unsupported format date: $dateString")
            }
        }

        override fun toString() = "JsonAdapter(Date)"
    }

    @JvmField
    internal val iso8601Format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
}
