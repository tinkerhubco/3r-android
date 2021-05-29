package com.tinkerhub.replenish.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val name: String,
    val userImageUrl: String,
    val rewardPoints: Int,
    val level: String
) : Parcelable