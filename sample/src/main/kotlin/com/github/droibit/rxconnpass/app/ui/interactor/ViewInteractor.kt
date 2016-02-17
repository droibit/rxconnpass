package com.github.droibit.rxconnpass.app.ui.interactor

/**
 *
 *
 * @author kumagai
 */
interface ViewInteractor<T> {
    fun init(view: T)
    fun onResume()
    fun onPause()
    fun onDetach()
}