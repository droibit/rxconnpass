package com.github.droibit.rxconnpass.app.ui.view.widget

import com.miguelcatalan.materialsearchview.MaterialSearchView

/**
 * Created by kumagai on 2016/02/15.
 */
interface SimpleOnQueryTextListener : MaterialSearchView.OnQueryTextListener {
    override fun onQueryTextChange(query: String) = false
}