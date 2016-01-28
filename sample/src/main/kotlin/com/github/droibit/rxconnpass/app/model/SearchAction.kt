package com.github.droibit.rxconnpass.app.model

import com.github.droibit.rxconnpass.app.model.data.ConnpassClient
import com.github.droibit.rxconnpass.emptyEventResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

/**
 *
 *
 * @author kumagai
 */
class SearchAction(private val client: ConnpassClient) : EventAction {

    fun searchByKeyword(keyword: String, searchMore: ConnpassClient.More? = null) {
        client.searchByKeyword(keyword, searchMore)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .onErrorReturn { t ->
                  Timber.e(t, "An error has occurred in keyword search.")
                  emptyEventResponse
              }
    }
}