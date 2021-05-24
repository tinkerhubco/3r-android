package com.tinkerhub.replenish.data.models

data class ActivityItem(
    val name: String,
    val organizer: String,
    val location: String,
    val eventDates: String,
    val participants: Int,
    val points: Int,
)