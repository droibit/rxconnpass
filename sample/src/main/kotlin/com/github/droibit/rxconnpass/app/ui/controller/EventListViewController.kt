package com.github.droibit.rxconnpass.app.ui.controller

import android.content.Context
import com.github.droibit.rxconnpass.app.databinding.FragmentEventListBinding
import com.github.droibit.rxconnpass.app.di.scope.PerEvent
import com.github.droibit.rxconnpass.app.model.SearchEventAction
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

/**
 *
 *
 * @author kumagai
 */
@PerEvent
class EventListViewController @Inject constructor(
        private val context: Context,
        private val action: SearchEventAction,
        private val compositeSubscription: CompositeSubscription): Lifecycle {

    private lateinit var binding: FragmentEventListBinding

    fun init(binding: FragmentEventListBinding) {
        this.binding = binding
    }

    override fun onResume() {
    }

    override fun onPause() {
    }
}