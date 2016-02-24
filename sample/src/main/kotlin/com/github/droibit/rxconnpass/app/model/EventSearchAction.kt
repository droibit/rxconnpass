package com.github.droibit.rxconnpass.app.model

import android.support.annotation.CheckResult
import android.support.annotation.VisibleForTesting
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.di.scope.PerEvent
import com.github.droibit.rxconnpass.app.model.data.api.ConnpassClient
import com.github.droibit.rxconnpass.app.model.data.reachability.Reachability
import com.github.droibit.rxconnpass.app.model.data.settings.Settings
import com.github.droibit.rxconnpass.app.model.data.settings.source.toOrder
import com.github.droibit.rxconnpass.app.model.exception.NetworkDisconnectedException
import rx.Observable
import rx.lang.kotlin.toObservable
import rx.lang.kotlin.toSingletonObservable
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 *
 *
 * @author kumagai
 */
// TODO: EventSearchActionに名前変更
@PerEvent
class EventSearchAction @Inject constructor(
        private val client: ConnpassClient,
        private val reachability: Reachability,
        private val settings: Settings) : SearchAction {

    // TODO: 回転した時にフィールドを復元しないと行けない

    override val canLoadMore: Boolean
        get() = moreSearch.canLoadMore

    @VisibleForTesting
    val moreSearch = ConnpassClient.MoreSearch()
    @VisibleForTesting
    var keyword: String = ""

    @CheckResult
    override fun search(param: String): Observable<List<Event>> {
        if (!keyword.equals(param)) {
            keyword = param
        }
        return searchByKeyword(newKeyword = true)
    }

    // TODO: RecyclerViewのスクロール制御しないとリストがからの時にリサーチできてしまう。
    @CheckResult
    override fun research(): Observable<List<Event>> = searchByKeyword(newKeyword = true)

    @CheckResult
    override fun searchMore(): Observable<List<Event>> = searchByKeyword(newKeyword = false)

    private fun searchByKeyword(newKeyword: Boolean): Observable<List<Event>> {
        if (newKeyword) {
            moreSearch.reset()
        } else {
            moreSearch.update(settings.countPerRequest)
        }

        return when {
            !reachability.connectedAny() -> NetworkDisconnectedException().toObservable()
            !newKeyword and !canLoadMore -> emptyList<Event>().toSingletonObservable()
            else -> {
                client.getByKeyword(keyword, settings.eventOrder.toOrder(), moreSearch)
                        .doOnNext { moreSearch.available = it.resultsAvailable }
                        .map { it.events }
                        .subscribeOn(Schedulers.io())
            }
        }
    }
}