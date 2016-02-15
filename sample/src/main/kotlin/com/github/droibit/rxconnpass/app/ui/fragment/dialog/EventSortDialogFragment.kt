package com.github.droibit.rxconnpass.app.ui.fragment.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import com.github.droibit.rxconnpass.app.R

/**
 *
 *
 * @author kumagai
 */
class EventSortDialogFragment : DialogFragment(), DialogInterface.OnClickListener {

    companion object {
        private val TAG = EventSortDialogFragment::class.java.simpleName
    }

    private lateinit var items: Array<String>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        items = resources.getStringArray(R.array.dialog_entries_event_sort_order)

        // TODO: デフォルトチェックはSettingsより
        return AlertDialog.Builder(context)
            .setTitle(R.string.dialog_title_event_sort_order)
            .setSingleChoiceItems(items, 1, this)
            .create()
    }

    override fun onDestroyView() {
        // http://stackoverflow.com/questions/11307390/dialogfragment-disappears-on-rotation-despite-setretaininstancetrue
        if (dialog != null && retainInstance) {
            dialog.setDismissMessage(null)
        }
        super.onDestroyView()
    }

    override fun onClick(dialog: DialogInterface, which: Int) {
        // TODO: 設定に保存
    }

    fun show(fm: FragmentManager) = show(fm, TAG)
}