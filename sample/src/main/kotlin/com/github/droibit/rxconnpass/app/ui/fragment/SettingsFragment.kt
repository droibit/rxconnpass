package com.github.droibit.rxconnpass.app.ui.fragment

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import com.github.droibit.rxconnpass.app.R

/**
 * Created by kumagai on 2016/01/19.
 */
class SettingsFragment: PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref_settings)
    }
}