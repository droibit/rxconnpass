package com.github.droibit.rxconnpass

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.util.*

/**
 * Created by kumagai on 2016/02/03.
 */
class EventTest {

    private val srcEvent = mockPartiallyMissingEvent.events.first()

    @Test
    fun overloadLimit() {
        val nullLimit = srcEvent.copy(limit = null)
        assertThat(nullLimit.isOverload).isFalse()

        val zeroLimit = srcEvent.copy(limit = 0)
        assertThat(zeroLimit.isOverload).isFalse()

        val lessLimit = srcEvent.copy(accepted = 1, limit = 10)
        assertThat(lessLimit.isOverload).isFalse()

        val reversed = srcEvent.copy(accepted = 11, limit = 10)
        assertThat(reversed.isOverload).isTrue()

        val overloadLimit = srcEvent.copy(accepted = 10, limit = 10)
        assertThat(overloadLimit.isOverload).isTrue()
    }

    @Test
    fun finishedEvent() {
        // c=2016/2/3 9:59:59:999, e=2016/2/3 10:00:00:000
        val notFinished = srcEvent.copy(endedAt = Date(1454461200000))
        assertThat(notFinished.isFinished(Date(1454461199999))).isFalse()

        // c, e=2016/2/3 10:00:00:000
        val finished1 = srcEvent.copy(endedAt = Date(1454461200000))
        assertThat(finished1.isFinished(Date(1454461200000))).isFalse()

        // c=2016/2/3 10:00:00:001 , e=2016/2/3 10:00:00:000
        val finished2 = srcEvent.copy(endedAt = Date(1454461200000))
        assertThat(finished2.isFinished(Date(1454461200001))).isTrue()
    }

    @Test
    fun hasCatchcopy() {
        val hasCacthcopy = srcEvent.copy(catchcopy = "テスト")
        assertThat(hasCacthcopy.hasCatchcopy).isTrue()

        val emptyCatchcopty = srcEvent.copy(catchcopy = "")
        assertThat(emptyCatchcopty.hasCatchcopy).isFalse()
    }

    @Test
    fun hasHashTag() {
        val hasHashTag = srcEvent.copy(hashTag = "#test")
        assertThat(hasHashTag.hasHashTag).isTrue()

        val emptyHashTag = srcEvent.copy(hashTag = "")
        assertThat(emptyHashTag.hasHashTag).isFalse()
    }

    @Test
    fun hasLatLon() {
        val hasLatLon = srcEvent.copy(lat = 0.0, lon = 0.0)
        assertThat(hasLatLon.hasLatLon).isTrue()

        val hasLat = srcEvent.copy(lat = 0.0, lon = null)
        assertThat(hasLat.hasLatLon).isFalse()

        val hasLon = srcEvent.copy(lat = null, lon = 0.0)
        assertThat(hasLon.hasLatLon).isFalse()

        val hasnot = srcEvent.copy(lat = null, lon = null)
        assertThat(hasnot.hasLatLon).isFalse()
    }
}