package com.github.droibit.rxconnpass.app.util.binding;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import android.databinding.BindingAdapter;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by kumagai on 2016/02/03.
 */
public class BindingAdapters {

    @BindingAdapter({"bind:onQueryText"})
    public static void bindQueryChangeListener(MaterialSearchView view, MaterialSearchView.OnQueryTextListener listener) {
        view.setOnQueryTextListener(listener);
    }

    @BindingAdapter({"bind:onScroll"})
    public static void bindScrollListener(RecyclerView view, RecyclerView.OnScrollListener listener) {
        view.addOnScrollListener(listener);
    }

    @BindingAdapter({"bind:onRefresh"})
    public static void bindRefreshListener(SwipeRefreshLayout layout, SwipeRefreshLayout.OnRefreshListener listener) {
        layout.setOnRefreshListener(listener);
    }

    @BindingAdapter({"bind:colorSchemeColor"})
    public static void bindColorSchemeColor(SwipeRefreshLayout layout, @ColorInt int color) {
        layout.setColorSchemeColors(color);
    }

    @BindingAdapter({"bind:offsetChanged"})
    public static void bindAppBarOffsetChanged(AppBarLayout layout, AppBarLayout.OnOffsetChangedListener listener) {
        layout.addOnOffsetChangedListener(listener);
    }

    @BindingAdapter({"bind:startedAt", "bind:endedAt"})
    public static void bindDateText(TextView view, Date startedAt, Date endedAt) {
        final java.text.DateFormat dateFormat = DateFormat.getDateFormat(view.getContext());
        view.setText(dateFormat.format(startedAt));
    }
}
