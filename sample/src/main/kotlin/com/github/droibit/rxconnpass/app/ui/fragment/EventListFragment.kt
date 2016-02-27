package com.github.droibit.rxconnpass.app.ui.fragment

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.RxConnpassApplication
import com.github.droibit.rxconnpass.app.databinding.FragmentEventListBinding
import com.github.droibit.rxconnpass.app.di.EventModule
import com.github.droibit.rxconnpass.app.ui.fragment.dialog.EventSortOrderDialogFragment
import com.github.droibit.rxconnpass.app.ui.interactor.EventListInteractor
import com.github.droibit.rxconnpass.app.ui.navigator.Navigator
import com.github.droibit.rxconnpass.app.ui.view.EventListView
import com.github.droibit.rxconnpass.app.ui.view.adapter.EventListAdapter
import com.github.droibit.rxconnpass.app.ui.view.transition.TransitionDetailEvent
import com.github.droibit.rxconnpass.app.ui.view.widget.DividerItemDecoration
import com.github.droibit.rxconnpass.app.ui.view.widget.DividerItemDecoration.Companion.VERTICAL_LIST
import com.github.droibit.rxconnpass.app.ui.view.widget.simpleOnQueryTextListener
import com.github.droibit.rxconnpass.app.ui.view.widget.simpleOnScrollListener
import com.github.droibit.rxconnpass.app.util.extension.isVisible
import com.github.droibit.rxconnpass.app.util.extension.show
import com.github.droibit.rxconnpass.app.util.extension.startAnimation
import com.github.droibit.rxconnpass.app.util.rx.RxBus
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.squareup.leakcanary.RefWatcher
import timber.log.Timber
import javax.inject.Inject

/**
 *
 *
 * @author kumagai
 */
class EventListFragment : Fragment(), EventListView, EventListView.Binding {

    companion object {

        @JvmStatic
        fun component() = RxConnpassApplication.component.plus(EventModule())
    }

    // SearchViewから検索ボタンが押された時に呼ばれる
    override val queryText: MaterialSearchView.OnQueryTextListener
        = simpleOnQueryTextListener { query -> onQueryTextSubmit(query) }
    // RecyclerViewがスクロールした時に呼ばれる
    override val scroll: RecyclerView.OnScrollListener
        = simpleOnScrollListener { view, x, y -> onRecyclerViewScrolled(view, x, y) }
    // PullToRefreshする時に呼ばれる
    override val refresh: SwipeRefreshLayout.OnRefreshListener
        = SwipeRefreshLayout.OnRefreshListener { onRefresh() }
    // SwipeRefresが誤爆するための対策
    // http://stackoverflow.com/questions/30779667/android-collapsingtoolbarlayout-and-swiperefreshlayout-get-stuck
    override val appBarOffsetChanged: AppBarLayout.OnOffsetChangedListener
        = AppBarLayout.OnOffsetChangedListener { layout, offset -> onAppBarVerticalOffsetChanged(offset) }

    @Inject
    internal lateinit var interactor: EventListInteractor
    @Inject
    internal lateinit var appContext: Context
    @Inject
    internal lateinit var eventBus: RxBus
    @Inject
    internal lateinit var refWatcher: RefWatcher

    private lateinit var binding: FragmentEventListBinding
    private lateinit var eventListAdapter: EventListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component().inject(this)

        eventListAdapter = EventListAdapter(callback = interactor.eventClick())
        retainInstance = true
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_list, container, false)
        binding.apply {
            listener = this@EventListFragment
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recycler.apply {
            adapter = eventListAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, VERTICAL_LIST))
            setHasFixedSize(true)

            binding.header.attachTo(this, true)
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

    override fun onDestroy() {
        super.onDestroy()
        refWatcher.watch(this)
    }

    override fun onDetach() {
        super.onDetach()
        interactor.destroy()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_event_list, menu)

        menu.findItem(R.id.action_search).apply {
            binding.searchView.setMenuItem(this)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings          -> interactor.settingsMenuClick()
        R.id.action_change_sort_order -> interactor.eventOrderMenuClick()
        else -> super.onContextItemSelected(item)
    }

    override fun showEventOrderDialog() {
        EventSortOrderDialogFragment().show(childFragmentManager)
    }

    override fun navigateToSetting() {
        // TODO: 更新中の場合の対策がいる
        Navigator.navigateToSettings(context)
    }

    override fun navigateToEventDetail(event: TransitionDetailEvent) {
        // TODO: 更新中の場合の対策がいる
        eventBus.send(event)
    }

    private fun onQueryTextSubmit(query: String): Boolean {
        if (query.isEmpty()) {
            return true
        }
        interactor.searchByKeyword(keyword = query)

        // falseを返すとSearchViewが閉じる
        return false
    }

    private fun onRecyclerViewScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
    }

    private fun onRefresh() {
        interactor.researchByKeyword()
    }

    private fun onAppBarVerticalOffsetChanged(offset: Int) {
        binding.swipeRefresh.isEnabled = offset == 0
    }

    override fun showContent(keyword: String?, events: List<Event>) {
        binding.apply {
            header.visibility = View.VISIBLE
            if (keyword != null) {
                headerText.text = keyword
            }
            eventListAdapter.call(events, false)

            val targetView = if (events.isNotEmpty()) recycler.apply { scrollToPosition(0) } else empty
            targetView.startAnimation(android.R.anim.fade_in) {
                visibility = View.VISIBLE
            }
        }
        Timber.d("Fetched ${events.size} events, keyword is $keyword.")
    }

    override fun showProgress() {
        setContentShown(shown = false)
    }

    override fun hideProgress() {
        setProgressShown(shown = false)
    }

    override fun hideRefreshProgress() {
        binding.swipeRefresh.apply {
            if (isRefreshing) {
                isRefreshing = false
            }
        }
    }

    override fun showError(t: Throwable) {
        Timber.e(t, "Event Fetched Error: ")

        binding.apply {
            header.visibility = View.VISIBLE
            headerText.text = ""
        }
        // TODO: イベントのクリア
        //eventListAdapter.
    }

    private fun setContentShown(shown: Boolean) {
        val (animRes, animAfterVisibility) = animationFor(shown)
        val contentView = binding.run { if (recycler.isVisible) recycler else empty }

        contentView.startAnimation(animRes) {
            visibility = animAfterVisibility
        }
        Timber.d("Shown($shown) Content.")

        setProgressShown(!shown)
    }

    private fun setProgressShown(shown: Boolean) {
        val (animRes, animAfterVisibility) = animationFor(shown)
        binding.progress.startAnimation(animRes) {
            visibility = animAfterVisibility
        }
        Timber.d("Shown($shown) progress.")
    }

    private fun animationFor(shown: Boolean) = if (shown) {
        Pair(android.R.anim.fade_in, View.VISIBLE)
    } else {
        Pair(android.R.anim.fade_out, View.GONE)
    }
}