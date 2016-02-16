package com.github.droibit.rxconnpass.app.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.droibit.rxconnpass.Event

/**
 *
 *
 * @author kumagai
 */
class EventDetailActivity : AppCompatActivity() {

    companion object {

        val EXTRA_EVENT = "event"

        fun launchIntent(context: Context, event: Event): Intent {
            return Intent(context, EventDetailActivity::class.java).apply {
                putExtra(EXTRA_EVENT, event)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}