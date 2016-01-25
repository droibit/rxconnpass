package com.github.droibit.rxconnpass.internal

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.util.*
import java.util.Collections.singletonList

class UtilsTest {

    @Test
    fun convertDateToYmdFormat() {
        val dates = singletonList(
                Date(1453042800000) // 2016/1/18 0:0:00
        )
        val formattedDates = dates.toYmdDateString()

        assertThat(formattedDates).apply {
            isNotEmpty()
            hasSize(1)
            containsAnyIn(singletonList("20160118"))
        }
    }

    @Test
    fun convertNullDateToYmdFormat() {
        val dates = listOf(
                Date(1451574000000), // 2016/1/01 00:00:00
                Date(1453042800000), // 2016/1/18 00:00:00
                Date(1454252399000)  // 2016/1/31 23:59:59
        )
        val formattedDates = dates.toYmdDateString()

        assertThat(formattedDates).apply {
            isNotEmpty()
            hasSize(3)
            containsAllIn(listOf("20160101", "20160118", "20160131"))
        }
    }

    @Test
    fun convertEmptyDateToYmdFormat() {
        val dates = emptyList<Date>()
        val formattedDates = dates.toYmdDateString()

        assertThat(formattedDates).isNull()
    }

    @Test
    fun convertDateToYmFormat() {
        val dates = singletonList(
                Date(1453042800000) // 2016/1/18 00:00:00
        )
        val formattedDates = dates.toYmDateString()

        assertThat(formattedDates).apply {
            isNotEmpty()
            hasSize(1)
            containsAnyIn(singletonList("201601"))
        }
    }

    @Test
    fun convertNullDateToYmFormat() {
        val dates = listOf(
                Date(1451573999000), // 2015/12/31 23:59:59
                Date(1453042800000), // 2016/01/18 00:00:00
                Date(1454252400000)  // 2016/02/01 00:00:00
        )
        val formattedDates = dates.toYmDateString()

        assertThat(formattedDates).apply {
            isNotEmpty()
            hasSize(3)
            containsAllIn(listOf("201512", "201601", "201602"))
        }
    }

    @Test
    fun convertEmptyDateToYmFormat() {
        val dates = emptyList<Date>()
        val formattedDates = dates.toYmDateString()

        assertThat(formattedDates).isNull()
    }
}