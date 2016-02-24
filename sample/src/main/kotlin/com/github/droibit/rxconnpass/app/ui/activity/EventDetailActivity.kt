package com.github.droibit.rxconnpass.app.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.ui.fragment.EventDetailFragment

/**
 *
 *
 * @author kumagai
 */
class EventDetailActivity : AppCompatActivity() {

    companion object {

        @JvmField
        val EXTRA_EVENT = "event"

        @JvmStatic
        fun launchIntent(context: Context, event: Event): Intent {
            return Intent(context, EventDetailActivity::class.java).apply {
                putExtra(EXTRA_EVENT, event)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        if (savedInstanceState == null) {
            val event = intent?.extras?.get(EXTRA_EVENT) as? Event ?: error("Event not exist.")
            val tag = getString(R.string.tag_event_detail_fragment)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.content, EventDetailFragment.newInstance(event), tag)
                    .commit()
        }
    }
}