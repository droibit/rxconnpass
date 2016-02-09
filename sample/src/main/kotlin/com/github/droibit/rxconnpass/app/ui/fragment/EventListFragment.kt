package com.github.droibit.rxconnpass.app.ui.fragment

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.RxConnpassApplication
import com.github.droibit.rxconnpass.app.databinding.FragmentEventListBinding
import com.github.droibit.rxconnpass.app.di.EventModule
import com.github.droibit.rxconnpass.app.ui.interactor.EventListInteractor
import com.github.droibit.rxconnpass.app.ui.navigator.Navigator
import com.github.droibit.rxconnpass.app.ui.view.EventListView
import com.github.droibit.rxconnpass.app.ui.view.adapter.EventListAdapter
import com.github.droibit.rxconnpass.app.ui.view.rx.MaterialSearchViewQueryTextEvent
import com.github.droibit.rxconnpass.app.ui.view.rx.queryTextChanges
import com.github.droibit.rxconnpass.app.ui.view.widget.DividerItemDecoration
import com.github.droibit.rxconnpass.app.ui.view.widget.DividerItemDecoration.Companion.VERTICAL_LIST
import com.github.droibit.rxconnpass.app.util.extension.startAnimation
import rx.Observable
import rx.functions.Action0
import rx.functions.Action1
import timber.log.Timber
import javax.inject.Inject

/**
 *
 *
 * @author kumagai
 */
class EventListFragment : Fragment(), EventListView {

    internal class ContentDelegate(
            private val contentView: RecyclerView,
            private val progressView: ProgressBar,
            private val emptyView: TextView
    ): Action1<List<Event>>, Action0 {

        // show content
        override fun call(events: List<Event>) {
            (contentView.adapter as? EventListAdapter)?.apply {
                call(events)
            }
            val targetView = if (events.isNotEmpty()) contentView else  emptyView
            targetView.startAnimation(android.R.anim.fade_in) {
                visibility = View.VISIBLE
            }
            progressView.startAnimation(android.R.anim.fade_out) {
                visibility = View.GONE
            }
            Timber.d("Show content (${events.size} events).")
        }

        // hide content
        override fun call() {
            realContentView().startAnimation(android.R.anim.fade_out) {
                visibility = View.GONE
            }
            progressView.startAnimation(android.R.anim.fade_in) {
                visibility = View.VISIBLE
            }
            Timber.d("Hide content and show progress.")
        }

        internal fun hide() = call()

        private fun realContentView() = if (contentView.visibility == View.VISIBLE) contentView else emptyView
    }

    companion object {

        @JvmStatic
        fun component() = RxConnpassApplication.component.plus(EventModule())
    }

    override val errorHandler: Action1<Throwable>
        get() = Action1 {  }

    override val searchViewTextChanges: Observable<MaterialSearchViewQueryTextEvent>
        get() = binding.searchView.queryTextChanges()

    override val showContent: Action1<List<Event>>
        get() = contentDelegate

    override val hideContent: Action0
        get() = contentDelegate


    @Inject
    internal lateinit var interactor: EventListInteractor
    @Inject
    internal lateinit var appContext: Context

    private lateinit var binding: FragmentEventListBinding
    private lateinit var eventListAdapter: EventListAdapter
    private lateinit var contentDelegate: ContentDelegate

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
            val activity = activity as? AppCompatActivity
            activity?.setSupportActionBar(toolbar)

            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        contentDelegate = ContentDelegate(
                contentView = binding.recycler,
                progressView = binding.progress,
                emptyView = binding.empty
        )
        eventListAdapter = EventListAdapter()

        binding.recycler.apply {
            adapter = eventListAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, VERTICAL_LIST))
            setHasFixedSize(true)
        }
        interactor.init(this)
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