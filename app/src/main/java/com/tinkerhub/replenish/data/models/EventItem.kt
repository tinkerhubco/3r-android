package com.tinkerhub.replenish.data.models

data class EventItem(
    val name: String,
    val organizer: String,
    val location: String,
    val eventDates: String,
    val points: Int
)