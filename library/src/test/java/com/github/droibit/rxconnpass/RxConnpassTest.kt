package com.github.droibit.rxconnpass

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Rule
import org.junit.Test
import retrofit2.adapter.rxjava.HttpException
import rx.observers.TestSubscriber

class RxConnpassTest {

    @Rule
    @JvmField
    val server = MockWebServer()

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun requestThenSuccess() {
        val service = RxConnpass.newConnpass("${server.url("/")}").service

        val mockResponse = MockResponse().apply {
            setBody(mockMultiEventResponse)
        }
        server.enqueue(mockResponse)

        val subscriber = TestSubscriber<EventResponse>()
        service.searchByKeyword("python").subscribe(subscriber)

        subscriber.assertCompleted()
        subscriber.assertNoErrors()
    }

    @Test
    fun requestThenNotFound() {
        val service = RxConnpass.newConnpass("${server.url("/")}").service

        val mockResponse = MockResponse().apply {
            setResponseCode(404)
            setBody(mockMultiEventResponse)
        }
        server.enqueue(mockResponse)

        val subscriber = TestSubscriber<EventResponse>()
        service.searchByKeyword("python").subscribe(subscriber)

        subscriber.assertError(HttpException::class.java)
    }
}