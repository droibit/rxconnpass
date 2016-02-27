package com.github.droibit.rxconnpass.app.ui.view

import android.support.design.widget.AppBarLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.ui.view.transition.TransitionDetailEvent
import com.miguelcatalan.materialsearchview.MaterialSearchView

/**
 *
 *
 * @author kumagai
 */
interface EventListView {

    interface Binding {
        val queryText: MaterialSearchView.OnQueryTextListener
        val refresh: SwipeRefreshLayout.OnRefreshListener
        val scroll: RecyclerView.OnScrollListener
        val appBarOffsetChanged: AppBarLayout.OnOffsetChangedListener
    }

    fun showContent(keyword: String? = null, events: List<Event>)
    fun showError(t: Throwable)

    fun showProgress()
    fun hideProgress()
    fun hideRefreshProgress()

    fun navigateToEventDetail(event: TransitionDetailEvent)
    fun showEventOrderDialog()
    fun navigateToSetting()
}