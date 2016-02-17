package com.github.droibit.rxconnpass.app.ui.interactor

import android.content.Context
import android.widget.Toast
import com.github.droibit.rxconnpass.app.di.scope.PerEvent
import com.github.droibit.rxconnpass.app.model.SearchAction
import com.github.droibit.rxconnpass.app.ui.view.EventListView
import rx.android.schedulers.AndroidSchedulers
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
        private val compositeSubscription: CompositeSubscription) : ViewInteractor<EventListView> {

    private lateinit var view: EventListView

    override fun attachView(view: EventListView) {
        this.view = view
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onDetach() {
        compositeSubscription.unsubscribe()
    }

    fun searchByKeyword(keyword: String) {
        // イベントの検索
        compositeSubscription += action.search(keyword)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(view.showProgress)
                .doOnCompleted(view.hideProgress)
                .subscribe(view.showContent, view.showError)
    }
}