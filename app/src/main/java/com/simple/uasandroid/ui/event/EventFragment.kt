package com.simple.uasandroid.ui.event

import EventDetailActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.*
import android.widget.ProgressBar
import com.google.gson.Gson
import com.simple.uasandroid.R
import com.simple.uasandroid.api.ApiRepository
import com.simple.uasandroid.model.Event
import com.simple.uasandroid.Util.invisible
import com.simple.uasandroid.Util.visible
import id.Anggie.livescore.ui.event.EventAdapter
import org.jetbrains.anko.support.v4.startActivity

class EventFragment : Fragment(), EventView {

    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: EventPresenter
    private lateinit var adapter: EventAdapter
    var event: String? = ""

//    private lateinit var searchView: SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_event, container, false)
        listEvent = view.findViewById(R.id.list_event)
        progressBar = view.findViewById(R.id.progress_bar)
//        setHasOptionsMenu(true)
        event = arguments?.getString("event")

        adapter = EventAdapter(requireContext(), events){
            startActivity<EventDetailActivity>(
                "id" to "${it.eventId}",
                "idhome" to "${it.idHome}",
                "idaway" to "${it.idAway}"
            )

            Log.d("test",it.eventId.toString()+ "||" + it.idHome.toString() + "||" +  it.idAway.toString())
        }

        listEvent.adapter = adapter
        val request = ApiRepository()
        val gson = Gson()
        presenter = EventPresenter(this, request, gson)
        presenter.getEventList("4328", event)
//        swipeRefresh.onRefresh {
//            presenter.getEventList("4328", event)
//        }
        return view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<Event>) {
//        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(event: String?): EventFragment {
            val fragment = EventFragment()
            val args = Bundle()
            args.putString("event",event)
            fragment.arguments = args
            return fragment
        }
    }
}
