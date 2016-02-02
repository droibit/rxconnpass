package com.github.droibit.rxconnpass.app.model

import android.support.annotation.CheckResult
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.di.scope.PerEvent
import com.github.droibit.rxconnpass.app.model.data.api.ConnpassClient
import com.github.droibit.rxconnpass.app.model.data.settings.Settings
import com.github.droibit.rxconnpass.emptyEventResponse
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 *
 *
 * @author kumagai
 */
@PerEvent
class SearchEventAction @Inject constructor(
        private val client: ConnpassClient,
        private val settings: Settings) : SearchAction {

    private var searchMore: ConnpassClient.More? = null

    @CheckResult
    override fun search(param: String): Observable<List<Event>> {
        return client.getByKeyword(param, searchMore)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn { t ->
                    Timber.e(t, "An error has occurred in keyword search.")
                    emptyEventResponse
                }
                .map { it.events }
    }
}