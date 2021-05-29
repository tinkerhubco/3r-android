package com.tinkerhub.replenish.data.models

import com.tinkerhub.replenish.data.interfaces.ItemDisplayItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RewardItem(
    override val _id: Int,
    override val title: String,
    override val mechanics: String,
    override val rewardPoints: Int,
    override val coverPhotoUrl: String,
    override val organizer: String
) : ItemDisplayItem {
    
    override val about: String? = null
    override val location: String? = null
    override val participantsCount: Int? = null
    override val hasJoined: Boolean? = null
    override val earnedPoints: Int? = null
    override val maxAttemptsCount: Int? = null
    override val attemptsCount: Int? = null
    override val startDate: String? = null
    override val endDate: String? = null
    override val scheduleTime: String? = null
}