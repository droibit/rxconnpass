package com.github.droibit.rxconnpass.app.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.RxConnpassApplication.Companion.component
import com.github.droibit.rxconnpass.app.ui.view.transition.TransitionDetailEvent
import com.github.droibit.rxconnpass.app.util.rx.RxBus
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.lang.kotlin.plusAssign
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

class EventListActivity : AppCompatActivity(), Action1<TransitionDetailEvent> {

    @Inject
    internal lateinit var eventBus: RxBus

    private lateinit var compositeSubscription: CompositeSubscription

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_list)

        component.inject(this)

        // TODO: 自前でフラグメントの追加がいるはず
    }

    override fun onStart() {
        super.onStart()

        compositeSubscription = CompositeSubscription()
        subscribeTransitionDetailEvent()
    }

    override fun onStop() {
        super.onStop()

        compositeSubscription.unsubscribe()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // TODO: フラグメントへ通知
    }

    private fun subscribeTransitionDetailEvent() {
        compositeSubscription += eventBus.toObserverable()
                .filter { it is TransitionDetailEvent }
                .cast(TransitionDetailEvent::class.java)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this)
    }

    override fun call(event: TransitionDetailEvent) {
        Toast.makeText(this, "Click Event: ${event.srcEvent.title}", Toast.LENGTH_SHORT).show()

        //Navigator.navigateToEventDetail(this, event)
    }
}