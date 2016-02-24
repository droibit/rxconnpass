package com.github.droibit.rxconnpass.app.ui.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.databinding.FragmentEventDetailBinding

/**
 *
 *
 * @author kumagai
 */
class EventDetailFragment : Fragment() {

    companion object {

        private val ARG_EVENT = "event"

        @JvmStatic
        fun newInstance(event: Event) = EventDetailFragment().apply {
            arguments = Bundle().apply { putSerializable(ARG_EVENT, event) }
        }
    }

    private val event: Event by lazy { arguments.getSerializable(ARG_EVENT) as Event }

    private lateinit var binding: FragmentEventDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_detail, container, false)
        binding.apply {
            val activity = activity as AppCompatActivity
            activity.setSupportActionBar(toolbar) {
                setHomeButtonEnabled(true)
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
                setDisplayShowTitleEnabled(false)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.event = event
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_event_detail, menu)
    }
}

private inline fun AppCompatActivity.setSupportActionBar(toolbar: Toolbar, init: ActionBar.()->Unit) {
    setSupportActionBar(toolbar)
    supportActionBar?.init()
}