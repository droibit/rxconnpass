package com.github.droibit.rxconnpass.app.ui.view.transition

import android.view.View
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.util.annotation.OpenForTesting

/**
 *
 *
 * @author kumagai
 */
@OpenForTesting
open class TransitionDetailEvent(
        val srcEvent: Event,
        val titleView: View
)