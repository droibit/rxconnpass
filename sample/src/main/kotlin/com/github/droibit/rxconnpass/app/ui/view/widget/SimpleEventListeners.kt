package com.github.droibit.rxconnpass.app.ui.view.widget

import android.support.v7.widget.RecyclerView
import com.miguelcatalan.materialsearchview.MaterialSearchView

inline fun simpleOnQueryTextListener(crossinline textSubmit: (query: String)->Boolean): MaterialSearchView.OnQueryTextListener {
    return object: MaterialSearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String) = textSubmit(query)

        override fun onQueryTextChange(p0: String) = false
    }
}

inline fun simpleOnScrollListener(crossinline scrolled: (recyclerView: RecyclerView, dx: Int, dy: Int)->Unit): RecyclerView.OnScrollListener {
    return object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            scrolled(recyclerView, dx, dy)
        }
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        }
    }
}