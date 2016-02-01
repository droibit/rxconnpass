package com.github.droibit.rxconnpass.app.model.data.settings.source

import android.content.Context
import android.content.SharedPreferences
import android.support.annotation.StringRes
import com.github.droibit.rxconnpass.app.R
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by kumagai on 2016/02/01.
 */
@Singleton
class PreferenceDataSource @Inject constructor(
        private val context: Context,
        private val sharedPrefs: SharedPreferences) : DataSource {

    override var countPerRequest: Int
        get() = getString(R.string.pref_general_request_per_count_key, "10").toInt()
        set(value) {
            setString(R.string.pref_general_request_per_count_key, "$value")
        }

    private fun getString(@StringRes key: Int, defaultValue: String)
            = sharedPrefs.getString(context.getString(key), defaultValue)

    private fun setString(@StringRes key: Int, value: String) {
        sharedPrefs.edit().putString(context.getString(key), value).apply()
    }
}