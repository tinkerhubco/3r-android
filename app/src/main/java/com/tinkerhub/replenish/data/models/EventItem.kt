package com.tinkerhub.replenish.data.models

import com.tinkerhub.replenish.data.interfaces.ItemDisplayItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventItem(
    override val _id: Int,
    override val title: String,
    override val about: String,
    override val mechanics: String,
    override val rewardPoints: Int,
    override val location: String,
    override val organizer: String,
    override val coverPhotoUrl: String,
    override val participantsCount: Int,
    override val hasJoined: Boolean,
    override val earnedPoints: Int,
    override val maxAttemptsCount: Int,
    override val attemptsCount: Int,
    override val startDate: String,
    override val endDate: String,
    override val scheduleTime: String
) : ItemDisplayItem