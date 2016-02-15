package com.github.droibit.rxconnpass.app.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.ui.view.transition.TransitionDetailEvent
import rx.functions.Action1

class EventListActivity : AppCompatActivity(), Action1<TransitionDetailEvent> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_event_list)

        // TODO: 自前でフラグメントの追加がいるはず
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // TODO: フラグメントへ通知
    }

    override fun call(event: TransitionDetailEvent) {

    }
}