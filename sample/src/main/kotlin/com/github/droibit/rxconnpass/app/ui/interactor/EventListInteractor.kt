package com.github.droibit.rxconnpass.app.ui.interactor

import android.content.Context
import com.github.droibit.rxconnpass.app.di.scope.PerEvent
import com.github.droibit.rxconnpass.app.model.SearchEventAction
import com.github.droibit.rxconnpass.app.ui.view.EventListView
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

/**
 *
 *
 * @author kumagai
 */
@PerEvent
class EventListInteractor @Inject constructor(
        private val context: Context,
        private val action: SearchEventAction,
        private val compositeSubscription: CompositeSubscription): Interactor {

    private lateinit var view: EventListView

    fun init(view: EventListView) {
        this.view = view
    }

    override fun onResume() {
    }

    override fun onPause() {
    }
}