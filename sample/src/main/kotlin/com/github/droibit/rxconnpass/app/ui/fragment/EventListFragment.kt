package com.github.droibit.rxconnpass.app.ui.fragment

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.RxConnpassApplication
import com.github.droibit.rxconnpass.app.databinding.FragmentEventListBinding
import com.github.droibit.rxconnpass.app.di.EventModule
import com.github.droibit.rxconnpass.app.ui.interactor.EventListInteractor
import com.github.droibit.rxconnpass.app.ui.navigator.Navigator
import com.github.droibit.rxconnpass.app.ui.view.EventListView
import com.github.droibit.rxconnpass.app.ui.view.adapter.EventListAdapter
import com.github.droibit.rxconnpass.app.ui.view.widget.DividerItemDecoration
import com.github.droibit.rxconnpass.app.ui.view.widget.DividerItemDecoration.Companion.VERTICAL_LIST
import com.github.droibit.rxconnpass.app.util.extension.cast
import rx.functions.Action1
import javax.inject.Inject

/**
 *
 *
 * @author kumagai
 */
class EventListFragment : Fragment(), EventListView {

    companion object {

        @JvmStatic
        fun component() = RxConnpassApplication.component.plus(EventModule())
    }

    override val showEventAction: Action1<List<Event>>
        get() = eventListAdapter
    override val errorHandler: Action1<Throwable>
        get() = throw UnsupportedOperationException()

    @Inject
    internal lateinit var interactor: EventListInteractor
    @Inject
    internal lateinit var appContext: Context

    private lateinit var binding: FragmentEventListBinding
    private lateinit var eventListAdapter: EventListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        component().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_list, container, false)

        return binding.run {
            activity.cast<AppCompatActivity> {
                setSupportActionBar(toolbar)
            }
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {

            eventListAdapter = EventListAdapter()
            recycler.apply {
                adapter = eventListAdapter
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(DividerItemDecoration(context, VERTICAL_LIST))
                setHasFixedSize(true)
            }
        }
        interactor.init(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)

        val item = menu.findItem(R.id.action_search)
        binding.searchView.setMenuItem(item)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                Navigator.navigateToSettings(context)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        interactor.onResume()
    }

    override fun onPause() {
        super.onPause()
        interactor.onPause()
    }
}