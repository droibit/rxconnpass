package com.github.droibit.rxconnpass.app.util.binding;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.github.droibit.rxconnpass.app.R;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import android.databinding.BindingAdapter;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.design.widget.AppBarLayout;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.widget.TextView;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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
        final java.text.DateFormat dateFormat = DateFormat.getLongDateFormat(view.getContext());
        final java.text.DateFormat timeFormat = DateFormat.getTimeFormat(view.getContext());

        final String endedAtText;
        final int formatRes;
        if (((endedAt.getTime() - startedAt.getTime()) / TimeUnit.DAYS.toMillis(1)) >= 1) {
            // 開始と終了日が異なる場合
            endedAtText = String.format("%s %s", dateFormat.format(startedAt), timeFormat.format(startedAt));
            formatRes = R.string.event_date_format_long;
        } else {
            endedAtText = timeFormat.format(endedAt);
            formatRes = R.string.event_date_format_short;
        }
        final String startedAtText = String.format("%s %s", dateFormat.format(startedAt), timeFormat.format(startedAt));
        view.setText(view.getContext().getString(formatRes, startedAtText, endedAtText));
    }

    @BindingAdapter({"bind:accepted", "bind:limit"})
    public static void bindParticipants(TextView view, int accepted, Integer limit) {
        if (limit == null) {
            view.setText(R.string.empty);
            return;
        }
        view.setText(view.getContext().getString(R.string.participant_format, accepted, limit));
    }

    @BindingAdapter({"bind:address", "bind:place"})
    public static void bindPlace(TextView view, String address, String place) {
        if (TextUtils.isEmpty(address) && TextUtils.isEmpty(place)) {
            view.setText(R.string.text_place_tbd); // To Be Determined（現在未決定だが、将来決定する）
            return;
        }
        view.setText(view.getContext().getString(R.string.event_place_format, address, place));
    }

    @BindingAdapter({"bind:onMapReady"})
    public static void bindMapReadyCallback(MapView view, OnMapReadyCallback callback) {
        view.getMapAsync(callback);
    }
}
