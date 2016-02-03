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
    fun checkOverloadLimit() {
        val nullLimit = srcEvent.copy(limit = null)
        assertThat(nullLimit.overload).isFalse()

        val zeroLimit = srcEvent.copy(limit = 0)
        assertThat(zeroLimit.overload).isFalse()

        val lessLimit = srcEvent.copy(accepted = 1, limit = 10)
        assertThat(lessLimit.overload).isFalse()

        val reversed = srcEvent.copy(accepted = 11, limit = 10)
        assertThat(reversed.overload).isTrue()

        val overloadLimit = srcEvent.copy(accepted = 10, limit = 10)
        assertThat(overloadLimit.overload).isTrue()
    }

    @Test
    fun checkFinishedEvent() {
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
}