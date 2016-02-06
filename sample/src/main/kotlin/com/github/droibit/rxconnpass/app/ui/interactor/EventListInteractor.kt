package com.github.droibit.rxconnpass.app.ui.interactor

import android.content.Context
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.di.scope.PerEvent
import com.github.droibit.rxconnpass.app.model.SearchAction
import com.github.droibit.rxconnpass.app.ui.view.EventListView
import rx.Observable
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
        // FIXME: 回転した時に再生成しないとダメ
        private val compositeSubscription: CompositeSubscription) : ViewInteractor {

    private lateinit var view: EventListView


    fun init(view: EventListView) {
        this.view = view
    }

    override fun onResume() {
        // TODO: プログレスの表示をどこに入れるか？
        compositeSubscription += view.searchViewTextChanges
                .filter { it.submitted && it.queryText.isNotEmpty() }
                .doOnNext { view.hideContent.call() }
                .concatMap { action.search("${it.queryText}") }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view.showContent, view.errorHandler)
    }

    override fun onPause() {
        compositeSubscription.unsubscribe()
    }
}