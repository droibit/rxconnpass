package com.github.droibit.rxconnpass.app.ui.view

import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.ui.view.rx.MaterialSearchViewQueryTextEvent
import rx.Observable
import rx.functions.Action1

/**
 *
 *
 * @author kumagai
 */
interface EventListView {

    val searchViewTextChanges: Observable<MaterialSearchViewQueryTextEvent>

    val prepareContent: Action1<String>
    val showContent: Action1<List<Event>>
    val errorHandler: Action1<Throwable>

    val itemClick: Observable<Event>
}