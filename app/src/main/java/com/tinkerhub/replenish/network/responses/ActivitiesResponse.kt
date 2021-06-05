package com.tinkerhub.replenish.network.responses

import com.tinkerhub.replenish.data.models.EventItem

data class ActivitiesResponse(
    val upcoming: List<EventItem>,
    val trending: List<EventItem>
)