package com.simple.uasandroid.ui.event

import com.google.gson.Gson
import com.simple.uasandroid.api.ApiRepository
import com.simple.uasandroid.api.TheSportDBApi
import com.simple.uasandroid.model.EventResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EventPresenter(
    eventFragment: EventFragment,
    request: ApiRepository,
    gson: Gson
) {
    fun getEventList(s: String, event: String?) {

    }

    class EventPresenter(private val view: EventView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson){
        fun getEventList(league: String?, event: String?){
            view.showLoading()
            doAsync {
                val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getEvent(league, event)),
                    EventResponse::class.java
                )

                uiThread {
                    view.hideLoading()
                    view.showEventList(data.events)
                }
            }
        }
    }
}