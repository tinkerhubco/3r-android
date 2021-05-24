package com.tinkerhub.replenish.data.models

import com.tinkerhub.replenish.data.interfaces.ItemDisplayItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventItem(
    override val name: String,
    override val organizer: String,
    override val location: String,
    override val eventDates: String,
    override val bannerImageUrl: String,
    override val description: String,
    val participants: Int,
    override val points: Int
) : ItemDisplayItem