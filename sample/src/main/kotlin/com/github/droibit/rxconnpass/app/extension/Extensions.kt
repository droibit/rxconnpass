package com.github.droibit.rxconnpass.app.extension

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