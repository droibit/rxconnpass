package com.github.droibit.rxconnpass.app.ui.view

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.github.droibit.rxconnpass.Event
import com.miguelcatalan.materialsearchview.MaterialSearchView
import rx.functions.Action0
import rx.functions.Action1

/**
 *
 *
 * @author kumagai
 */
interface EventListView {

    interface Binding {
        val queryText: MaterialSearchView.OnQueryTextListener
        val scroll: RecyclerView.OnScrollListener
        val refresh: SwipeRefreshLayout.OnRefreshListener
    }

    val showContent: Action1<List<Event>>
    val showError: Action1<Throwable>

    val showProgress: Action0
    val hideProgress: Action0
}