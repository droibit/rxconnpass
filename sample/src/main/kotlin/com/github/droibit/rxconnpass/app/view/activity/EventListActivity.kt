package com.github.droibit.rxconnpass.app.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.droibit.rxconnpass.app.view.binding.EventListActivityBinding

class EventListActivity : AppCompatActivity() {

    private lateinit var binding: EventListActivityBinding

    /** {@inheritDoc}  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = EventListActivityBinding(this)
    }
}
