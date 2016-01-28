package com.github.droibit.rxconnpass.app.ui.fragment

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.RxConnpassApplication
import com.github.droibit.rxconnpass.app.databinding.FragmentEventListBinding
import com.github.droibit.rxconnpass.app.di.DaggerEventComponent
import com.github.droibit.rxconnpass.app.di.EventComponent
import com.github.droibit.rxconnpass.app.di.EventModule
import com.github.droibit.rxconnpass.app.di.MockNetworkModule
import com.github.droibit.rxconnpass.app.ui.activity.EventListActivity
import com.github.droibit.rxconnpass.app.ui.controller.EventListViewController
import com.github.droibit.rxconnpass.app.util.extension.castAs
import javax.inject.Inject

/**
 *
 *
 * @author kumagai
 */
class EventListFragment : Fragment() {

    companion object {

        fun component(): EventComponent {
            return DaggerEventComponent.builder()
                    .appComponent(RxConnpassApplication.component)
                    .eventModule(EventModule())
                    .mockNetworkModule(MockNetworkModule())
                    .build()
        }
    }

    @Inject
    private lateinit var viewController: EventListViewController

    private lateinit var binding: FragmentEventListBinding

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
            activity.castAs<AppCompatActivity> {
                setSupportActionBar(toolbar)
            }
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recycler.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_main, menu)

        val item = menu.findItem(R.id.action_search)
        binding.searchView.setMenuItem(item)
    }
}