package com.simple.uasandroid.ui.event

import com.simple.uasandroid.model.Event

interface EventView{
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>)
}