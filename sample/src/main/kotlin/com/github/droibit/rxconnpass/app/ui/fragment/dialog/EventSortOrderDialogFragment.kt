package com.github.droibit.rxconnpass.app.ui.fragment.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDialogFragment
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.RxConnpassApplication.Companion.component
import com.github.droibit.rxconnpass.app.model.data.settings.Settings
import timber.log.Timber
import javax.inject.Inject

/**
 *
 *
 * @author kumagai
 */
class EventSortOrderDialogFragment : AppCompatDialogFragment(), DialogInterface.OnClickListener {

    @Inject
    internal lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component.inject(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val items = resources.getStringArray(R.array.dialog_entries_event_sort_order)
        return AlertDialog.Builder(context)
                .setTitle(R.string.dialog_title_event_sort_order)
                .setSingleChoiceItems(items, settings.eventSortOrder, this)
                .create()
    }

    override fun onClick(dialog: DialogInterface, which: Int) {
        Timber.d("Event sort order changed: $which")
        settings.eventSortOrder = which
        dismiss()
    }
}