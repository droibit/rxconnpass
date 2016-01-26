package com.github.droibit.rxconnpass.app.ui.event.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.droibit.rxconnpass.app.ui.event.list.EventListActivityBinding

class EventListActivity : AppCompatActivity() {

    private lateinit var binding: EventListActivityBinding

    /** {@inheritDoc}  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = EventListActivityBinding(this)
    }
}
