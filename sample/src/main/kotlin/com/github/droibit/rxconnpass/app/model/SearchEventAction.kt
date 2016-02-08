package com.github.droibit.rxconnpass.app.model

import android.support.annotation.CheckResult
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.di.scope.PerEvent
import com.github.droibit.rxconnpass.app.model.data.api.ConnpassClient
import com.github.droibit.rxconnpass.app.model.data.reachability.Reachability
import com.github.droibit.rxconnpass.app.model.data.settings.Settings
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
@PerEvent
class SearchEventAction @Inject constructor(
        private val client: ConnpassClient,
        private val reachability: Reachability,
        private val settings: Settings) : SearchAction {

    // TODO: 回転した時にフィールドを復元しないと行けない

    private var searchMore: ConnpassClient.More? = null

    @CheckResult
    override fun search(param: String): Observable<List<Event>> {
        if (!reachability.connectedAny()) {
            return Observable.error(NetworkDisconnectedException())
        }
        return client.getByKeyword(param, searchMore)
                //.onBackpressureBuffer()
                .subscribeOn(Schedulers.io())
                .delay(3, TimeUnit.SECONDS)
                .map { it.events }
    }
}