package com.github.droibit.rxconnpass.app.ui.interactor

import android.content.Context
import com.github.droibit.rxconnpass.app.di.scope.PerEvent
import com.github.droibit.rxconnpass.app.model.SearchAction
import com.github.droibit.rxconnpass.app.ui.view.EventListView
import rx.lang.kotlin.plusAssign
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject
import javax.inject.Named

/**
 *
 *
 * @author kumagai
 */
@PerEvent
class EventListInteractor @Inject constructor(
        private val context: Context,
        @Named("searchEvent") private val action: SearchAction,
        private val compositeSubscription: CompositeSubscription) : ViewInteractor {

    private lateinit var view: EventListView

    fun init(view: EventListView) {
        this.view = view
    }

    override fun onResume() {
        compositeSubscription += action.search("hogehoge")
                .subscribe(view.showEventAction)
    }

    override fun onPause() {
        compositeSubscription.unsubscribe()
    }
}