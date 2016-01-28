package com.github.droibit.rxconnpass.app.util.extension

import android.app.Activity
import android.support.v4.app.Fragment
import com.github.droibit.rxconnpass.app.RxConnpassApplication
import java.io.InputStream

/**
 * Created by kumagai on 2016/01/21.
 */

fun Activity.connpassApp() = applicationContext as RxConnpassApplication
fun Fragment.connpassApp() = context.applicationContext as RxConnpassApplication

fun InputStream.readText() = reader().use { it.readText() }

inline fun <reified T> Any.castAs(f: T.()->Unit) {
    val casted = this as T
    casted?.f()
}