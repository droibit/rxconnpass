package com.droibit.github.rxconnpass

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import rx.observers.TestSubscriber

public class RxConnpassTest {

    private val _server = MockWebServer()

    @Rule
    public fun getServer() = _server

    @Test
    public fun requestSuccess() {
        val service = RxConnpass.newConnpass().service

        _server.enqueue(MockResponse().setBody(mockResponse))

        val subscriber = TestSubscriber<EventResponse>()
        service.search("python").subscribe(subscriber)

        subscriber.assertNoErrors()
        //subscriber.assertReceivedOnNext()
    }
}