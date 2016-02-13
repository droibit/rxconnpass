package com.github.droibit.rxconnpass.app.util.binding;

import com.github.droibit.rxconnpass.app.R;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by kumagai on 2016/02/03.
 */
public class BindingAdapters {

    @BindingAdapter({"bind:accepted", "bind:limit"})
    public static void bindParticipantText(TextView view, int accepted, Integer limit) {
        final Context context = view.getContext();

        String acceptedText, limitText;
        boolean overload = false;
        if (limit != null) {
            acceptedText = String.valueOf(accepted);
            limitText = String.valueOf(limit);
            overload = limit != 0 && accepted >= limit;
        } else {
            acceptedText = limitText = context.getString(R.string.empty);
        }
        final String text = context.getString(R.string.participant_format, acceptedText, limitText);
        view.setText(text);

        final int colorRes = textColor(overload);
        view.setTextColor(ContextCompat.getColor(context, colorRes));
    }

    @BindingAdapter({"bind:startedAt", "bind:endedAt"})
    public static void bindDateText(TextView view, Date startedAt, Date endedAt) {
        final java.text.DateFormat dateFormat = DateFormat.getDateFormat(view.getContext());
        view.setText(dateFormat.format(startedAt));
    }

    @ColorRes
    private static int textColor(boolean useRedColor) {
        return (useRedColor) ? R.color.material_red_a100 : R.color.secondary_text_default_material_light;
    }
}
