package com.github.droibit.rxconnpass.app.ui.interactor

import android.content.Context
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.di.scope.PerEvent
import com.github.droibit.rxconnpass.app.model.SearchAction
import com.github.droibit.rxconnpass.app.ui.view.EventListView
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.plusAssign
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named
import kotlin.concurrent.currentThread

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
        // https://medium.com/@LiudasSurvila/droidcon-2015-london-part-1-698a6b750f30#.jmmlh8eq6
        compositeSubscription += view.searchViewTextChanges
                .filter { it.submitted && it.queryText.isNotEmpty() }
                .concatMap { searchEvent(it.queryText.toString()) }
                .observeOn(AndroidSchedulers.mainThread())
                //.subscribeOn(Schedulers.io())
                .doOnSubscribe(view.showProgress)
                .subscribe(view.showContent, view.errorHandler)
    }

    override fun onPause() {
        compositeSubscription.unsubscribe()
    }

    fun searchEvent(keyword: String): Observable<List<Event>> {
        // TODO: ネットワークチェックはActionに投げる?
        Timber.d("search keyword :$keyword: ${currentThread.name}")
        return action.search(keyword)
    }
}