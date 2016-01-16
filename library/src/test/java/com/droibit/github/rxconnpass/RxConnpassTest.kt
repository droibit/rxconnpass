package com.droibit.github.rxconnpass

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import rx.observers.TestSubscriber

public class RxConnpassTest {

    @Rule
    @JvmField
    public val server = MockWebServer()

    @Test
    public fun requestThenSuccess() {
        val service = RxConnpass.newClient("${server.url("/")}").service

        val mockResponse = MockResponse().apply {
            setBody(mockMultiEventResponse)
        }
        server.enqueue(mockResponse)

        val subscriber = TestSubscriber<EventResponse>()
        service.search("python").subscribe(subscriber)

        subscriber.assertNoErrors()
    }

    @Test
    public fun requestThenNotFound() {
        val service = RxConnpass.newClient("${server.url("/")}").service

        val mockResponse = MockResponse().apply {
            setResponseCode(404)
            setBody(mockMultiEventResponse)
        }
        server.enqueue(mockResponse)

        val subscriber = TestSubscriber<EventResponse>()
        service.search("python").subscribe(subscriber)

        subscriber.assertError(HttpException::class.java)
    }

    @Test
    public fun requestThenParseEvent() {
        val service = RxConnpass.newClient("${server.url("/")}").service

        val mockResponse = MockResponse().apply {
            setBody(mockSingleEventResponse)
        }
        server.enqueue(mockResponse)

        val subscriber = TestSubscriber<EventResponse>()
        service.search("python").subscribe(subscriber)

        // FIXME:
        subscriber.assertNoErrors()
        //subscriber.assertReceivedOnNext(listOf(singleEvent))
    }
}