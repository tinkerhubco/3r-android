package com.tinkerhub.replenish.network.responses

import com.tinkerhub.replenish.data.models.RewardItem
import com.tinkerhub.replenish.data.models.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClaimPointsResponse(
    private val id: String,
    private val _firstName: String,
    private val _lastName: String,
    private val _avatarUrl: String,
    private val _points: Int,
    private val _level: String,
    val vouchers: List<RewardItem>
): User(
    _id = id,
    firstName = _firstName,
    lastName = _lastName,
    avatarUrl = _avatarUrl,
    points = _points,
    level = _level
)