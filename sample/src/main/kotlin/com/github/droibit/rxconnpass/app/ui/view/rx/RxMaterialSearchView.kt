package com.github.droibit.rxconnpass.app.ui.view.rx

import android.support.annotation.CheckResult
import com.jakewharton.rxbinding.internal.Preconditions.checkUiThread
import com.miguelcatalan.materialsearchview.MaterialSearchView
import rx.Observable
import rx.Subscriber
import rx.android.MainThreadSubscription

@CheckResult
fun MaterialSearchView.queryTextChanges(): Observable<MaterialSearchViewQueryTextEvent> {
    return RxMaterialSearchView.queryTextChanges(this)
}

/**
 * Created by kumagai on 2016/02/04.
 */
object RxMaterialSearchView {

    @JvmStatic
    @CheckResult
    fun queryTextChanges(view: MaterialSearchView): Observable<MaterialSearchViewQueryTextEvent> {
        return Observable.create(MaterialSearchViewQueryTextChangesOnSubscribe(view))
    }
}

class MaterialSearchViewQueryTextChangesOnSubscribe(private val view: MaterialSearchView)
        : Observable.OnSubscribe<MaterialSearchViewQueryTextEvent> {

    override fun call(subscriber: Subscriber<in MaterialSearchViewQueryTextEvent>) {
        checkUiThread()

        val watcher = object: MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!subscriber.isUnsubscribed) {
                    subscriber.onNext(MaterialSearchViewQueryTextEvent(view, query, true))
                    return false
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (!subscriber.isUnsubscribed) {
                    subscriber.onNext(MaterialSearchViewQueryTextEvent(view, newText, false))
                }
                return true
            }
        }
        view.setOnQueryTextListener(watcher)

        subscriber.add(object: MainThreadSubscription(){
            override fun onUnsubscribe() {
                view.setOnQueryTextListener(null)
            }
        })
        // TODO: クエリを取り出せないので復元用に何処かで保持しておかなければいけない
        subscriber.onNext(MaterialSearchViewQueryTextEvent(view, "", false))
    }
}

data class MaterialSearchViewQueryTextEvent(
        val view: MaterialSearchView,
        val queryText: CharSequence,
        val submitted: Boolean)