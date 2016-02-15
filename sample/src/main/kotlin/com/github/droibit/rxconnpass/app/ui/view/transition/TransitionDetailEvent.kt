package com.github.droibit.rxconnpass.app.ui.view.transition

import android.view.View
import com.github.droibit.rxconnpass.Event

/**
 *
 *
 * @author kumagai
 */
data class TransitionDetailEvent(
        val event: Event,
        val titleView: View
)