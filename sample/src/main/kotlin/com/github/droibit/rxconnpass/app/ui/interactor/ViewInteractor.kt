package com.github.droibit.rxconnpass.app.ui.interactor

/**
 *
 *
 * @author kumagai
 */
interface ViewInteractor<T> {
    fun attachView(view: T)
    fun onResume()
    fun onPause()
    fun onDetach()
}