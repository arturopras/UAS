package com.simple.uasandroid.detail

import com.simple.uasandroid.model.EventDetail

interface EventDetailView{
    fun showLoading()
    fun hideLoading()
    fun showEventDetail(data: List<EventDetail>)
}