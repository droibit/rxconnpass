package com.github.droibit.rxconnpass.app.model.data.api

import com.github.droibit.rxconnpass.app.model.data.api.ConnpassClient.MoreSearch
import com.google.common.truth.Truth.assertThat
import org.junit.Test


/**
 * Created by kumagai on 2016/02/23.
 */
class MoreSearchTest {

    @Test
    fun correctCanLoadMore() {
        val more = MoreSearch().apply {
            available = 15
        }

        more.update(count = 5)
        assertThat(more.count).isEqualTo(5)
        assertThat(more.start).isEqualTo(5)
        assertThat(more.canLoadMore).isTrue()

        more.update(count = 5)
        assertThat(more.count).isEqualTo(5)
        assertThat(more.start).isEqualTo(10)
        assertThat(more.canLoadMore).isTrue()

        more.update(count = 5)
        assertThat(more.count).isEqualTo(5)
        assertThat(more.start).isEqualTo(15)
        assertThat(more.canLoadMore).isFalse()
    }

    @Test(expected = IllegalStateException::class)
    fun countLessThanZero() {
        MoreSearch().update(count = 0)
    }

    @Test
    fun reset() {
        val more = MoreSearch().apply {
            start = 5
            available = 15
            count = 5
        }

        more.reset()
        assertThat(more.available).isEqualTo(0)
        assertThat(more.start).isEqualTo(0)
        assertThat(more.count).isEqualTo(0)
    }
}