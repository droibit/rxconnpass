package com.github.droibit.rxconnpass.app.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.view.View
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.RxConnpassApplication.Companion.component
import com.github.droibit.rxconnpass.app.model.data.settings.Settings
import com.github.droibit.support.prefbinding.bindPreference
import com.squareup.leakcanary.RefWatcher
import javax.inject.Inject

/**
 * Created by kumagai on 2016/01/19.
 */
class SettingsFragment : PreferenceFragmentCompat() {

    private val eventCountPref: Preference by bindPreference(R.string.pref_general_request_per_count_key)

    @Inject
    internal lateinit var settings: Settings
    @Inject
    internal lateinit var refWatcher: RefWatcher

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        component.inject(this)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref_settings)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventCountPref.apply {
            setOnPreferenceChangeListener { p, v ->
                p.summary = context.getString(R.string.pref_general_request_per_count_summary, "$v")
                true
            }
            summary = context.getString(R.string.pref_general_request_per_count_summary, "${settings.countPerRequest}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        refWatcher.watch(this)
    }
}