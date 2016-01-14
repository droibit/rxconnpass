package com.droibit.github.rxconnpass

import android.net.Uri
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

internal object ResponseAdapters {

    val factory: JsonAdapter.Factory = JsonAdapter.Factory { type, annotations, moshi ->
        when (type) {
            Date::class.java -> dateJsonAdapter
            Uri::class.java  -> uriJsonAdapter
            else -> null
        }
    }
}

private val iso8601Format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
private val dateJsonAdapter = object: JsonAdapter<Date>() {
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

private val uriJsonAdapter = object: JsonAdapter<Uri>() {
    override fun toJson(writer: JsonWriter, value: Uri) {}

    override fun fromJson(reader: JsonReader) = Uri.parse(reader.nextString())

    override fun toString() = "JsonAdapter(Uri)"
}
