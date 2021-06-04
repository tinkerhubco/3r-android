package com.tinkerhub.replenish.data.managers

import com.tinkerhub.replenish.data.database.EcoHeroDb
import com.tinkerhub.replenish.data.models.User

class DatabaseManager(ecoHeroDb: EcoHeroDb) : IDatabaseManager {
    private val userDao = ecoHeroDb.userDao()
    
    override suspend fun setUser(user: User) {
        userDao.setCurrent(user)
    }
    
    override suspend fun getCurrentUser(): User? {
        return userDao.getCurrent()
    }
}

interface IDatabaseManager {
    suspend fun setUser(user: User)
    suspend fun getCurrentUser(): User?
}