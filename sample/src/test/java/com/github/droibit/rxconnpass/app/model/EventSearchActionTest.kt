package com.github.droibit.rxconnpass.app.model

import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.EventResponse
import com.github.droibit.rxconnpass.app.model.data.api.ConnpassClient
import com.github.droibit.rxconnpass.app.model.data.api.source.DataSource
import com.github.droibit.rxconnpass.app.model.data.reachability.Reachability
import com.github.droibit.rxconnpass.app.model.data.settings.Settings
import com.github.droibit.rxconnpass.app.model.exception.NetworkDisconnectedException
import com.github.droibit.rxconnpass.app.util.RxSchedulersOverrideRule
import com.github.droibit.rxconnpass.app.util.anyObject
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.junit.MockitoJUnit
import rx.lang.kotlin.toSingletonObservable
import rx.observers.TestSubscriber
import com.github.droibit.rxconnpass.app.model.data.reachability.source.DataSource as ReachabilityDataSource
import com.github.droibit.rxconnpass.app.model.data.settings.source.DataSource as SettingsDataSource

/**
 * Created by kumagai on 2016/02/22.
 */
class EventSearchActionTest {

    private val mockResponse = EventResponse(
            resultsAvailable = 30,
            resultsReturned = 10,
            resultsStart = 0,
            events = emptyList()
    )

    @Rule
    @JvmField
    val mockito = MockitoJUnit.rule()
    @Rule
    @JvmField
    val overrideSchedulers = RxSchedulersOverrideRule()

    @Mock
    lateinit var dataSource: DataSource
    @Mock
    lateinit var reachabilityDataSource: ReachabilityDataSource
    @Mock
    lateinit var settingsDataSource: SettingsDataSource

    lateinit var searchAction: EventSearchAction

    @Before
    fun setup() {
        searchAction = EventSearchAction(
                client = ConnpassClient(dataSource),
                reachability = Reachability(reachabilityDataSource),
                settings = Settings(settingsDataSource)
        )
        `when`(settingsDataSource.countPerRequest).thenReturn(10)
    }

    @Test
    fun holdKeyword() {
        `when`(dataSource.getByKeyword(anyString(), anyObject(), anyObject()))
                .thenReturn(mockResponse.toSingletonObservable())
        `when`(reachabilityDataSource.connectedAny()).thenReturn(true)

        searchAction.search("kotlin")
        assertThat(searchAction.keyword).isEqualTo("kotlin")

        searchAction.searchMore()
        assertThat(searchAction.keyword).isEqualTo("kotlin")

        searchAction.research()
        assertThat(searchAction.keyword).isEqualTo("kotlin")

        searchAction.search("android")
        assertThat(searchAction.keyword).isEqualTo("android")
    }

    @Test
    fun correctCanLoadMore() {
        `when`(dataSource.getByKeyword(anyString(), anyObject(), anyObject()))
                .thenReturn(mockResponse.toSingletonObservable())
        `when`(reachabilityDataSource.connectedAny()).thenReturn(true)

        // resultsAvailable = 30,
        // resultsReturned = 10,
        // count = 10
        searchAction.search("kotlin").subscribe()
        assertThat(searchAction.canLoadMore).isTrue()

        searchAction.searchMore().subscribe()
        assertThat(searchAction.canLoadMore).isTrue()

        searchAction.searchMore().subscribe()
        assertThat(searchAction.canLoadMore).isFalse()
    }

    @Test
    fun subscribeEvents() {
        `when`(dataSource.getByKeyword(anyString(), anyObject(), anyObject()))
                .thenReturn(mockResponse.toSingletonObservable())
        `when`(reachabilityDataSource.connectedAny()).thenReturn(true)

        val searchSubscriber = TestSubscriber<List<Event>>()
        searchAction.search("kotlin").subscribe(searchSubscriber)

        searchSubscriber.run {
            assertNoErrors()
            assertCompleted()
            assertUnsubscribed()
            assertValueCount(1)
            assertValue(mockResponse.events)
        }

        val moreSearchSubscriber = TestSubscriber<List<Event>>()
        searchAction.research().subscribe(moreSearchSubscriber)

        moreSearchSubscriber.run {
            assertNoErrors()
            assertCompleted()
            assertUnsubscribed()
            assertValueCount(1)
            assertValue(mockResponse.events)
        }
    }

    @Test
    fun cantMoreSubscribeEvents() {
        `when`(reachabilityDataSource.connectedAny()).thenReturn(true)

        searchAction.keyword = "kotlin"
        searchAction.searchMore.apply {
            start = 10
            count = 10
            available = 10
        }

        val subscriber = TestSubscriber<List<Event>>()
        searchAction.searchMore().subscribe(subscriber)

        subscriber.run {
            assertNoErrors()
            assertCompleted()
            assertUnsubscribed()
            assertValueCount(1)
            assertValue(emptyList())
        }
    }

    @Test
    fun networkDisconnected() {
        `when`(reachabilityDataSource.connectedAny()).thenReturn(false)

        val subscriber = TestSubscriber<List<Event>>()
        searchAction.searchMore().subscribe(subscriber)

        subscriber.run {
            assertError(NetworkDisconnectedException::class.java)
            assertNotCompleted()
            assertUnsubscribed()
        }
    }
}