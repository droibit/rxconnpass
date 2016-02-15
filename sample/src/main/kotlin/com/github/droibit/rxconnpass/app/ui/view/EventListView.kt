package com.github.droibit.rxconnpass.app.ui.view

import com.github.droibit.rxconnpass.Event
import rx.Observable
import rx.functions.Action0
import rx.functions.Action1

/**
 *
 *
 * @author kumagai
 */
interface EventListView {

    val showContent: Action1<List<Event>>
    val showError: Action1<Throwable>

    val showProgress: Action0
    val hideProgress: Action0

    val itemClick: Observable<Event>
}