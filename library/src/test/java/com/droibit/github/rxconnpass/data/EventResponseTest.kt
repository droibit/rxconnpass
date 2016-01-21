package com.droibit.github.rxconnpass.data

import com.droibit.github.rxconnpass.*
import com.droibit.github.rxconnpass.ResponseAdapters
import com.squareup.moshi.Moshi
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class EventResponseTest {

    val adapter = Moshi.Builder()
                       .add(ResponseAdapters.factory)
                       .build()
                       .adapter(EventResponse::class.java)

    @Test
    fun parseSingleEvent() {
        val event = adapter.fromJson(mockSingleEventResponse)
        assertThat(event).isEqualTo(mockSingleEvent)
    }

    @Test
    fun parsePartiallyMissingEvent() {
        val event = adapter.fromJson(mockPartiallyMissingResponse)
        assertThat(event).isEqualTo(mockPartiallyMissingEvent)
    }

    @Test
    fun parseNoEvent() {
        val event = adapter.fromJson(mockNoEventResponse)
        assertThat(event).isEqualTo(mockNoEvent)
    }
}