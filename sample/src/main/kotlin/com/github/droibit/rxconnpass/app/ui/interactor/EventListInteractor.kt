package com.github.droibit.rxconnpass.app.ui.interactor

import com.github.droibit.rxconnpass.app.di.scope.PerEvent
import com.github.droibit.rxconnpass.app.model.SearchAction
import com.github.droibit.rxconnpass.app.ui.view.EventListView
import com.github.droibit.rxconnpass.app.ui.view.transition.TransitionDetailEvent
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.lang.kotlin.plusAssign
import rx.subscriptions.CompositeSubscription
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

/**
 *
 *
 * @author kumagai
 */
@PerEvent
class EventListInteractor @Inject constructor(
        @Named("searchEvent") private val action: SearchAction,
        private val compositeSubscription: CompositeSubscription) : ViewInteractor<EventListView> {

    private lateinit var view: EventListView

    override fun init(view: EventListView) {
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
        Timber.d("Search keyword by $keyword.")

        // イベントの検索
        compositeSubscription += action.search(keyword)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(view.showProgress())
                .doOnCompleted(view.hideProgress())
                .subscribe(view.showContent(), view.showError())
    }

    fun researchByKeyword() {
        Timber.d("Research keyword.")

        // TODO: 詳細へ遷移した場合はキャンセルしないといけない
        compositeSubscription += action.research()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(view.hideRefreshProgress())
                .subscribe(view.showContent(), view.showError())
    }

    fun eventClick(): Action1<TransitionDetailEvent> = Action1 {
        view.navigateToEventDetail(it)
    }

    fun eventOrderMenuClick(): Boolean {
        view.showEventOrderDialog()
        return true
    }

    fun settingsMenuClick(): Boolean {
        view.navigateToSetting()
        return true
    }
}