package com.github.droibit.rxconnpass.app.ui.navigator

import android.content.Context
import com.github.droibit.rxconnpass.app.ui.activity.SettingsActivity

/**
 *
 *
 * @author kumagai
 */
object Navigator {

    @JvmStatic
    fun navigateToSettings(context: Context?) {
        context?.let {
            it.startActivity(SettingsActivity.launchIntent(it))
        }
    }
}