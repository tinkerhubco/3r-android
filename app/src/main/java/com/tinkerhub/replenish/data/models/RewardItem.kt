package com.tinkerhub.replenish.data.models

import com.google.gson.annotations.SerializedName
import com.tinkerhub.replenish.data.interfaces.ItemDisplayItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RewardItem(
    override val _id: String,
    
    @SerializedName("name")
    override val title: String,
    override val mechanics: String,
    
    @SerializedName("points")
    override val rewardPoints: Int,
    override val coverPhotoUrl: String,
    
    @SerializedName("partner")
    override val organizer: Organizer,
    val activityId: String
) : ItemDisplayItem {
    
    companion object {
        fun getDefault() = RewardItem(
            _id = "",
            title = "",
            mechanics = "",
            rewardPoints = -1,
            coverPhotoUrl = "",
            organizer = Organizer(
                name = "",
                avatarUrl = null
            ),
            activityId = ""
        )
    }
    
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