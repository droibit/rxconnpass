package com.droibit.github.rxconnpass

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import rx.observers.TestSubscriber
import kotlin.jvm.internal.Intrinsics

public class RxConnpassTest {

    private val _server = MockWebServer()

    @Rule
    public fun getServer() = _server

    @Test
    public fun requestThenSuccess() {
        val service = RxConnpass.newClient("${_server.url("/")}").service

        val mockResponse = MockResponse().apply {
            setBody(mockMultiEventResponse)
        }
        _server.enqueue(mockResponse)

        val subscriber = TestSubscriber<EventResponse>()
        service.search("python").subscribe(subscriber)

        subscriber.assertNoErrors()
    }

    @Test
    public fun requestThenNotFound() {
        val service = RxConnpass.newClient("${_server.url("/")}").service

        val mockResponse = MockResponse().apply {
            setResponseCode(404)
            setBody(mockMultiEventResponse)
        }
        _server.enqueue(mockResponse)

        val subscriber = TestSubscriber<EventResponse>()
        service.search("python").subscribe(subscriber)

        subscriber.assertError(HttpException::class.java)
    }

    @Test
    public fun requestThenParseEvent() {
        val service = RxConnpass.newClient("${_server.url("/")}").service

        val mockResponse = MockResponse().apply {
            setBody(mockSingleEventResponse)
        }
        _server.enqueue(mockResponse)

        val subscriber = TestSubscriber<EventResponse>()
        service.search("python").subscribe(subscriber)

        // FIXME:
        subscriber.assertNoErrors()
        subscriber.assertReceivedOnNext(listOf(singleEvent))
    }
}