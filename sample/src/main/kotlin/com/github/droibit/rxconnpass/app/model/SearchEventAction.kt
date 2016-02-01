package com.github.droibit.rxconnpass.app.model

import com.github.droibit.rxconnpass.app.di.scope.PerEvent
import com.github.droibit.rxconnpass.app.model.api.ConnpassClient
import com.github.droibit.rxconnpass.emptyEventResponse
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
class SearchEventAction @Inject constructor(private val client: ConnpassClient) : EventAction {

    fun searchByKeyword(keyword: String, searchMore: ConnpassClient.More? = null) {
        client.getByKeyword(keyword, searchMore)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .onErrorReturn { t ->
                  Timber.e(t, "An error has occurred in keyword search.")
                  emptyEventResponse
              }
    }
}