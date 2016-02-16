package com.github.droibit.rxconnpass.app.util.extension

import android.support.annotation.AnimRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.View
import android.view.animation.AnimationUtils
import java.io.InputStream

/**
 * Created by kumagai on 2016/01/21.
 */

fun InputStream.readText() = reader().use { it.readText() }

inline fun View.startAnimation(@AnimRes resId: Int, after: View.() -> Unit) {
    startAnimation(AnimationUtils.loadAnimation(context, resId))
    after()
}

val View.isVisible: Boolean
    get() = visibility == View.VISIBLE

inline fun <reified T : DialogFragment> T.show(fm : FragmentManager) {
    this.show(fm, T::class.java.simpleName)
}