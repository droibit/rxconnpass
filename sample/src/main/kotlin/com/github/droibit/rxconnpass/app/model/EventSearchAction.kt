package com.github.droibit.rxconnpass.app.model

import android.support.annotation.CheckResult
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.di.scope.PerEvent
import com.github.droibit.rxconnpass.app.model.data.api.ConnpassClient
import com.github.droibit.rxconnpass.app.model.data.reachability.Reachability
import com.github.droibit.rxconnpass.app.model.data.settings.Settings
import com.github.droibit.rxconnpass.app.model.data.settings.source.toOrder
import com.github.droibit.rxconnpass.app.model.exception.NetworkDisconnectedException
import rx.Observable
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit
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
        get() = searchMore.canLoadMore

    private val searchMore = ConnpassClient.SearchMore()
    private var keyword: String = ""

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
            searchMore.reset()
        }
        searchMore.count = settings.countPerRequest

        if (!reachability.connectedAny()) {
            return Observable.error(NetworkDisconnectedException())
        }
        return client.getByKeyword(keyword, settings.eventOrder.toOrder(), searchMore)
                .doOnNext { searchMore.update(it.resultsAvailable) }
                .map { it.events }
                .subscribeOn(Schedulers.io())
    }
}