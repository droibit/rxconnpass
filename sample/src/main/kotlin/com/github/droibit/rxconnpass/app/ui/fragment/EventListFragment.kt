package com.github.droibit.rxconnpass.app.ui.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.databinding.FragmentEventListBinding

/**
 *
 *
 * @author kumagai
 */
class EventListFragment: Fragment() {

    private lateinit var binding: FragmentEventListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
        }
    }
}