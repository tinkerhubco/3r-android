package com.tinkerhub.replenish.data.interfaces

import android.os.Parcelable

interface ItemDisplayItem: Parcelable {
    val _id: Int
    val title: String
    val about: String?
    val mechanics: String
    val rewardPoints: Int
    val location: String?
    val organizer: String?
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