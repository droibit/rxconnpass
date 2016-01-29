package com.github.droibit.rxconnpass.app.ui.controller

import android.content.Context
import com.github.droibit.rxconnpass.app.databinding.FragmentEventListBinding
import com.github.droibit.rxconnpass.app.di.scope.PerActivity
import com.github.droibit.rxconnpass.app.model.SearchAction
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

/**
 *
 *
 * @author kumagai
 */
@PerActivity
class EventListViewController @Inject constructor(
        private val context: Context,
        private val action: SearchAction,
        private val compositeSubscription: CompositeSubscription): Lifecycle {

    private lateinit var binding: FragmentEventListBinding

    fun init(binding: FragmentEventListBinding) {
        this.binding = binding
    }

    override fun onResume() {
        throw UnsupportedOperationException()
    }

    override fun onPause() {
        throw UnsupportedOperationException()
    }
}