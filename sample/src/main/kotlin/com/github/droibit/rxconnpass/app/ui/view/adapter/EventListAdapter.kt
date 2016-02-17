package com.github.droibit.rxconnpass.app.ui.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.databinding.RecyclerItemEventBinding
import com.github.droibit.rxconnpass.app.ui.view.transition.TransitionDetailEvent
import com.github.droibit.rxconnpass.app.util.rx.RxBus
import rx.functions.Action1
import rx.functions.Action2
import java.util.*

/**
 * Created by kumagai on 2016/02/03.
 */
class EventListAdapter(private val callback: Action1<TransitionDetailEvent>)
        : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Action2<List<Event>, Boolean> {

    companion object {
        val VIEW_ITEM = 0
        val VIEW_FOOTER = 1
    }

    class ItemViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        internal val binding: RecyclerItemEventBinding = DataBindingUtil.bind(root)
    }

    class FooterViewHolder(root: View) : RecyclerView.ViewHolder(root)

    private var events: MutableList<Event> = ArrayList()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> holder.binding.event = events[position]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_ITEM -> {
                val view = inflater.inflate(R.layout.recycler_item_event, parent, false)
                ItemViewHolder(view).apply {
                    view.setOnClickListener {
                        val event = events[adapterPosition]
                        callback.call(TransitionDetailEvent(event, titleView = binding.title))
                    }
                }
            }
            VIEW_FOOTER -> {
                val view = inflater.inflate(R.layout.recycler_item_event_footer, parent, false)
                FooterViewHolder(view)
            }
            else -> error("Unknown view type: $viewType")
        }
    }

    override fun getItemCount() = events.size

    override fun getItemViewType(position: Int): Int {
        return if (events.getOrNull(position) != null) VIEW_ITEM else VIEW_FOOTER
    }

    override fun call(events: List<Event>, append: Boolean) {
        if (!append) {
            this.events = events.toMutableList()
            notifyDataSetChanged()
            return
        }

        val lastIndex = this.events.size
        this.events.addAll(events)
        notifyItemRangeInserted(lastIndex, events.size)
    }
}