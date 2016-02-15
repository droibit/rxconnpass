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
import com.github.droibit.rxconnpass.app.ui.view.widget.SimpleOnQueryTextListener
import com.github.droibit.rxconnpass.app.util.extension.isVisible
import com.github.droibit.rxconnpass.app.util.extension.startAnimation
import rx.Observable
import rx.functions.Action0
import rx.functions.Action1
import rx.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

/**
 *
 *
 * @author kumagai
 */
class EventListFragment : Fragment(), EventListView, SimpleOnQueryTextListener {

    companion object {

        @JvmStatic
        fun component() = RxConnpassApplication.component.plus(EventModule())
    }

    @Inject
    internal lateinit var interactor: EventListInteractor
    @Inject
    internal lateinit var appContext: Context

    private lateinit var binding: FragmentEventListBinding
    private lateinit var eventListAdapter: EventListAdapter
    private lateinit var itemClickListener: PublishSubject<Event>

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
            (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        itemClickListener = PublishSubject.create()
        eventListAdapter = EventListAdapter(itemClickListener)

        binding.recycler.apply {
            adapter = eventListAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, VERTICAL_LIST))
            setHasFixedSize(true)
        }
        interactor.init(this)
    }

    override fun onResume() {
        super.onResume()
        interactor.onResume()
    }

    override fun onPause() {
        super.onPause()
        interactor.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        itemClickListener.onCompleted()
        binding.searchView.setOnQueryTextListener(null)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)

        binding.searchView.apply {
            val item = menu.findItem(R.id.action_search)
            setMenuItem(item)
            setOnQueryTextListener(this@EventListFragment)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> Navigator.navigateToSettings(context)
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        if (query.isEmpty()) {
            return true
        }
        updateToolbarTitle(title = query)
        interactor.searchByKeyword(keyword = query)

        // falseを返すとSearchViewが閉じる
        return false
    }

    private fun updateToolbarTitle(title: String) {
        var actionbar = (activity as? AppCompatActivity)?.supportActionBar
        if (actionbar != null) {
            actionbar.title = title
            Timber.d("Update toolbar title: $title")
        }
    }

    private fun updateEventList(events: List<Event>) {
        eventListAdapter.call(events, false)

        // TODO: 位置を先頭に戻す

        val targetView = binding.run { if (events.isNotEmpty()) recycler else empty }
        targetView.startAnimation(android.R.anim.fade_in) {
            visibility = View.VISIBLE
        }
        setProgressShown(shown = false)

        Timber.d("Fetched ${events.size} events.")
    }

    private fun setContentShown(shown: Boolean) {
        val (animRes, animAfterVisibility) = animationFor(shown)
        val contentView =  binding.run { if (recycler.isVisible) recycler else empty }

        contentView.startAnimation(animRes) {
            visibility = animAfterVisibility
        }
        setProgressShown(!shown)
    }

    private fun setProgressShown(shown: Boolean) {
        val (animRes, animAfterVisibility) = animationFor(shown)
        binding.progress.startAnimation(animRes) {
            visibility = animAfterVisibility
        }
        Timber.d("Hide content and show progress.")
    }

    private fun animationFor(shown: Boolean) = if (shown) {
        Pair(android.R.anim.fade_in, View.VISIBLE)
    } else {
        Pair(android.R.anim.fade_out, View.GONE)
    }

    override val showError: Action1<Throwable>
        get() = Action1 { Timber.e(it, "Event Fetched Error: ") }

    override val showContent: Action1<List<Event>>
        get() = Action1 { updateEventList(it) }

    override val itemClick: Observable<Event>
        get() = itemClickListener

    override val showProgress: Action0
        get() = Action0 { setContentShown(shown = false) }
    override val hideProgress: Action0
        get() = Action0 { setProgressShown(shown = false) }
}