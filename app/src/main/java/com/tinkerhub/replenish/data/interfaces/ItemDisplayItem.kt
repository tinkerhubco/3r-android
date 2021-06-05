package com.tinkerhub.replenish.data.interfaces

import android.os.Parcelable
import com.tinkerhub.replenish.data.models.Organizer

interface ItemDisplayItem: Parcelable {
    val _id: String
    val title: String
    val about: String?
    val mechanics: String
    val rewardPoints: Int
    val location: String?
    val organizer: Organizer?
    val coverPhotoUrl: String
    val participantsCount: Int?
    val hasJoined: Boolean?
    val earnedPoints: Int?
    val maxAttemptsCount: Int?
    val attemptsCount: Int?
    val startDate: String?
    val endDate: String?
    val scheduleTime: String?
}