package com.github.droibit.rxconnpass.app.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.droibit.rxconnpass.app.R

class EventListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_event_list)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // TODO: フラグメントへ通知
    }
}