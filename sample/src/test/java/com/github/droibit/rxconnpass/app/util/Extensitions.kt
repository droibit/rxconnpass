package com.github.droibit.rxconnpass.app.util

import org.mockito.Mockito

/**
 * Created by kumagai on 2016/02/22.
 */

inline fun <reified T : Any> _mock(): T = Mockito.mock(T::class.java)

/**
 * https://discuss.kotlinlang.org/t/how-to-use-mockito-with-kotlin/324/15
 */
fun <T> uninitialized(): T = null as T
