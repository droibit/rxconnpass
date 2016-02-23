package com.github.droibit.rxconnpass.app.model.data.api

import com.github.droibit.rxconnpass.app.model.data.api.ConnpassClient.SearchMore
import com.google.common.truth.Truth.assertThat
import org.junit.Test


/**
 * Created by kumagai on 2016/02/23.
 */
class SearchMoreTest {

    @Test
    fun correctCanLoadMore() {
        val more = SearchMore().apply {
            count = 5
        }

        more.update(available = 15)
        assertThat(more.available).isEqualTo(15)
        assertThat(more.start).isEqualTo(5)
        assertThat(more.canLoadMore).isTrue()

        more.update(available = 15)
        assertThat(more.available).isEqualTo(15)
        assertThat(more.start).isEqualTo(10)
        assertThat(more.canLoadMore).isTrue()

        more.update(available = 15)
        assertThat(more.available).isEqualTo(15)
        assertThat(more.start).isEqualTo(15)
        assertThat(more.canLoadMore).isFalse()
    }

    @Test
    fun reset() {
        val more = SearchMore().apply {
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