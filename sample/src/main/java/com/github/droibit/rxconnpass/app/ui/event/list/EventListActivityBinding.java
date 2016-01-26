package com.github.droibit.rxconnpass.app.ui.event.list;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.droibit.rxconnpass.app.R;
import com.github.droibit.rxconnpass.app.databinding.ActivityEventListBinding;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

/**
 * Created by kumagai on 2016/01/25.
 */
public class EventListActivityBinding {

    private final ActivityEventListBinding binding;

    public EventListActivityBinding(EventListActivity activity) {
        binding = DataBindingUtil.setContentView(activity, R.layout.activity_event_list);
    }

    public View getRoot() {
        return binding.getRoot();
    }

    public Toolbar getToolbar() {
        return binding.toolbar;
    }

    public MaterialSearchView getSearchView() {
        return binding.searchView;
    }
}
