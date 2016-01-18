package com.droibit.github.rxconnpass

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import rx.observers.TestSubscriber

class RxConnpassTest {

    @Rule
    @JvmField
    val server = MockWebServer()

    @Test
    fun requestThenSuccess() {
        val service = RxConnpass.newClient("${server.url("/")}").service

        val mockResponse = MockResponse().apply {
            setBody(mockMultiEventResponse)
        }
        server.enqueue(mockResponse)

        val subscriber = TestSubscriber<EventResponse>()
        service.searchByKeyword("python").subscribe(subscriber)

        subscriber.assertNoErrors()
    }

    @Test
    fun requestThenNotFound() {
        val service = RxConnpass.newClient("${server.url("/")}").service

        val mockResponse = MockResponse().apply {
            setResponseCode(404)
            setBody(mockMultiEventResponse)
        }
        server.enqueue(mockResponse)

        val subscriber = TestSubscriber<EventResponse>()
        service.searchByKeyword("python").subscribe(subscriber)

        subscriber.assertError(HttpException::class.java)
    }

    @Test
    fun checkParsedEventResponse() {
        val service = RxConnpass.newClient("${server.url("/")}").service

        val mockResponse = MockResponse().apply {
            setBody(mockSingleEventResponse)
        }
        server.enqueue(mockResponse)

        val subscriber = TestSubscriber<EventResponse>()
        service.searchByKeyword("python").subscribe(subscriber)

        subscriber.assertNoErrors()
        subscriber.assertReceivedOnNext(listOf(singleEvent))
    }
}