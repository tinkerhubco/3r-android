package com.tinkerhub.replenish.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Organizer(
    val name: String,
    val avatarUrl: String?
): Parcelable