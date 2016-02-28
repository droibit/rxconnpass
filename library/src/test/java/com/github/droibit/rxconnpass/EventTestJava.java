package com.github.droibit.rxconnpass;

/**
 * @author kumagai
 */
public class EventTestJava {

    public void call() {
        final EventResponse response = MockResponse.getMockPartiallyMissingEvent();
        final Event event = response.events.get(0);

        event.getHasCatchcopy();
        event.getHasHashTag();
        event.getHasLatLon();
        event.isOverload();
    }
}
