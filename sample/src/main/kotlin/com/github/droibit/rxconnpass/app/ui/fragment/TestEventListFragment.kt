package com.github.droibit.rxconnpass.app.ui.fragment

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.preference.PreferenceManager
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.databinding.FragmentEventListBinding
import com.github.droibit.rxconnpass.app.model.SearchEventAction
import com.github.droibit.rxconnpass.app.model.data.api.ConnpassClient
import com.github.droibit.rxconnpass.app.model.data.api.source.MockDataSource
import com.github.droibit.rxconnpass.app.model.data.reachability.Reachability
import com.github.droibit.rxconnpass.app.model.data.reachability.source.MockNetworkDataSource
import com.github.droibit.rxconnpass.app.model.data.settings.Settings
import com.github.droibit.rxconnpass.app.model.data.settings.source.PreferenceDataSource
import com.github.droibit.rxconnpass.app.ui.view.adapter.EventListAdapter
import com.github.droibit.rxconnpass.app.ui.view.rx.queryTextChanges
import com.github.droibit.rxconnpass.app.ui.view.widget.DividerItemDecoration
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.lang.kotlin.plusAssign
import rx.subjects.PublishSubject
import rx.subscriptions.CompositeSubscription

/**
 * Created by kumagai on 2016/02/09.
 */
class TestEventListFragment: Fragment() {

    private lateinit var searchAction: SearchEventAction
    private lateinit var binding: FragmentEventListBinding
    private lateinit var eventListAdapter: EventListAdapter
    private lateinit var contentDelegate: EventListFragment.ContentDelegate
    private lateinit var compositeSubscription: CompositeSubscription
    private lateinit var itemClickListener: PublishSubject<Event>

    val errorHandler: Action1<Throwable>
        get() = Action1 { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        searchAction = SearchEventAction(
                client = ConnpassClient(source = MockDataSource(context)),
                reachability = Reachability(source = MockNetworkDataSource(connected = true)),
                settings = Settings(source = PreferenceDataSource(context, sharedPrefs))
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_list, container, false)

        return binding.run {
            (activity as? AppCompatActivity)?.apply {
                setSupportActionBar(toolbar)
            }
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        contentDelegate = EventListFragment.ContentDelegate(
                contentView = binding.recycler,
                progressView = binding.progress,
                emptyView = binding.empty
        )
        eventListAdapter = EventListAdapter(listener = itemClickListener)

        binding.recycler.apply {
            adapter = eventListAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST))
            setHasFixedSize(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)

        val item = menu.findItem(R.id.action_search)
        binding.searchView.setMenuItem(item)
    }

    override fun onResume() {
        super.onResume()

        compositeSubscription = CompositeSubscription()

        compositeSubscription += binding.searchView.queryTextChanges()
                .filter { it.submitted && it.queryText.isNotEmpty() }
                .doOnNext { contentDelegate.hide() }
                .concatMap { searchAction.search("${it.queryText}") }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contentDelegate, errorHandler)
    }

    override fun onPause() {
        super.onPause()

        compositeSubscription.unsubscribe()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}