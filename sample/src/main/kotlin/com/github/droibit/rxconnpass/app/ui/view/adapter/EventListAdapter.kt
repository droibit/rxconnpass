package com.github.droibit.rxconnpass.app.ui.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.databinding.RecyclerItemEventBinding
import rx.functions.Action1
import java.util.*

/**
 * Created by kumagai on 2016/02/03.
 */
class EventListAdapter : RecyclerView.Adapter<EventListAdapter.ViewHolder>(), Action1<List<Event>> {

    class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        internal val binding: RecyclerItemEventBinding = DataBindingUtil.bind(root)
    }

    private var events: List<Event> = ArrayList()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            event = events[position]
            //executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_event, parent, false)
        return ViewHolder(root)
    }

    override fun getItemCount() = events.size

    override fun call(events: List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }
}