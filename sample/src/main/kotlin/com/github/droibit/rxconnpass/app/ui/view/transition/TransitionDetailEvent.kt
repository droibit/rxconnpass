package com.github.droibit.rxconnpass.app.ui.view.transition

import android.support.annotation.VisibleForTesting
import android.view.View
import com.github.droibit.rxconnpass.Event

/**
 *
 *
 * @author kumagai
 */
@VisibleForTesting
open class TransitionDetailEvent(
        val srcEvent: Event,
        val titleView: View
)