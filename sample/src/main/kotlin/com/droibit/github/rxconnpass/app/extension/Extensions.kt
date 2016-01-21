package com.droibit.github.rxconnpass.app.extension

import android.app.Activity
import android.support.v4.app.Fragment
import com.droibit.github.rxconnpass.app.RxConnpassApplication

/**
 * Created by kumagai on 2016/01/21.
 */

fun Activity.connpassApp() = applicationContext as RxConnpassApplication
fun Fragment.connpassApp() = context.applicationContext as RxConnpassApplication