package com.github.droibit.rxconnpass.app.ui.navigator

import android.app.Activity
import android.content.Context
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat.makeSceneTransitionAnimation
import android.support.v4.util.Pair
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.ui.activity.EventDetailActivity
import com.github.droibit.rxconnpass.app.ui.activity.SettingsActivity
import com.github.droibit.rxconnpass.app.ui.view.transition.TransitionDetailEvent

/**
 *
 *
 * @author kumagai
 */
object Navigator {

    @JvmStatic
    fun navigateToSettings(context: Context?): Boolean {
        context?.run { startActivity(SettingsActivity.launchIntent(this)) }
        return true
    }

    @JvmStatic
    fun navigateToEventDetail(activity: Activity?, transitionEvent: TransitionDetailEvent): Boolean {
        if (activity != null) {
            val sharedElement = Pair(transitionEvent.titleView, activity.getString(R.string.tag_event_title))
            val options = makeSceneTransitionAnimation(activity, sharedElement).toBundle()
            val intent = EventDetailActivity.launchIntent(activity, transitionEvent.srcEvent)
            ActivityCompat.startActivity(activity, intent, options)
        }
        return true
    }
}