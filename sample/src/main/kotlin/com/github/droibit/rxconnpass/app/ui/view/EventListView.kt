package com.github.droibit.rxconnpass.app.ui.view

import com.github.droibit.rxconnpass.Event
import rx.functions.Action1

/**
 *
 *
 * @author kumagai
 */
interface EventListView {

    val showEventAction: Action1<List<Event>>

    val errorHandler: Action1<Throwable>
}