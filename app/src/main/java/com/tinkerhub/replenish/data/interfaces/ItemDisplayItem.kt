package com.tinkerhub.replenish.data.interfaces

import android.os.Parcelable

interface ItemDisplayItem: Parcelable {
    val name: String
    val organizer: String
    val location: String
    val eventDates: String
    val description: String
    val bannerImageUrl: String
    val points: Int
}