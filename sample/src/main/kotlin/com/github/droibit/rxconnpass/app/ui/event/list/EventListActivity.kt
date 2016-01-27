package com.github.droibit.rxconnpass.app.ui.event.list

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.databinding.ActivityEventListBinding
import com.github.droibit.rxconnpass.app.ui.event.list.EventListActivityBinding

class EventListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventListBinding

    /** {@inheritDoc}  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_list)
    }
}
