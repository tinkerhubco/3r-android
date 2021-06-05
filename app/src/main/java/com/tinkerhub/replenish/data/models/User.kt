package com.tinkerhub.replenish.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class User(
    @PrimaryKey
    val _id: String,
    val firstName: String,
    val lastName: String,
    val avatarUrl: String,
    val points: Int,
    val level: String
) : Parcelable {
    
    companion object {
        fun getDefault() = User(
            _id = "",
            firstName = "",
            lastName = "",
            avatarUrl = "",
            points = -1,
            level = ""
        )
    }
}