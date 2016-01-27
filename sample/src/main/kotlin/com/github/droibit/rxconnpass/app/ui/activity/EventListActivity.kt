package com.github.droibit.rxconnpass.app.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.databinding.ActivityEventListBinding
import com.miguelcatalan.materialsearchview.MaterialSearchView

class EventListActivity : AppCompatActivity() {

    val toolbar: Toolbar
        get() = binding.toolbar
    val searchView: MaterialSearchView
        get() = binding.searchView

    private lateinit var binding: ActivityEventListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_list)
        setSupportActionBar(toolbar)
    }

    override fun onBackPressed() {
        if (searchView.isSearchOpen) {
            searchView.closeSearch()
        } else {
            super.onBackPressed()
        }
    }
}