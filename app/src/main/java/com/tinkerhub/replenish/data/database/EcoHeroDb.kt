package com.tinkerhub.replenish.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tinkerhub.replenish.BuildConfig
import com.tinkerhub.replenish.data.database.dao.UserDao
import com.tinkerhub.replenish.data.models.User

@Database(
    entities = [
        User::class
    ],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
abstract class EcoHeroDb : RoomDatabase() {
    abstract fun userDao(): UserDao
}