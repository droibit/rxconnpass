package com.github.droibit.rxconnpass.app.ui.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.R
import com.github.droibit.rxconnpass.app.databinding.FragmentEventDetailBinding
import com.github.droibit.rxconnpass.app.ui.view.EventDetailView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import timber.log.Timber

/**
 *
 *
 * @author kumagai
 */
class EventDetailFragment : Fragment(), EventDetailView, EventDetailView.Binding {

    companion object {

        private val ARG_EVENT = "event"

        @JvmStatic
        fun newInstance(event: Event): EventDetailFragment {
            val fragment = EventDetailFragment()
            fragment.arguments = Bundle().apply { putSerializable(ARG_EVENT, event) }
            return fragment
        }
    }

    private val event: Event by lazy { arguments.getSerializable(ARG_EVENT) as Event }

    private lateinit var binding: FragmentEventDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_detail, container, false)
        binding.apply {
            val activity = activity as AppCompatActivity
            activity.setSupportActionBar(toolbar) {
                setHomeButtonEnabled(true)
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
                setDisplayShowTitleEnabled(false)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            event = this@EventDetailFragment.event
            listener = this@EventDetailFragment
        }
        binding.content.map.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_event_detail, menu)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        Timber.d("Called #onMapReady()")

        val lat = event.lat
        val lon = event.lon
        if (lat != null && lon != null) {
            googleMap.moveCamera(lat, lon)
            googleMap.addMarker(lat, lon)

            Timber.d("Event Place: $lat, $lon")
        }
    }
}

private inline fun AppCompatActivity.setSupportActionBar(toolbar: Toolbar, init: ActionBar.()->Unit) {
    setSupportActionBar(toolbar)
    supportActionBar?.init()
}

private fun GoogleMap.moveCamera(lat: Double, lon: Double) = moveCamera(CameraUpdateFactory.newLatLng(LatLng(lat, lon)))
private fun GoogleMap.addMarker(lat: Double, lon: Double) {
    addMarker(MarkerOptions().position(LatLng(lat, lon)))
}