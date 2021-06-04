package com.tinkerhub.replenish.sources.activity

class ActivityRepository(
    private val remoteSource: IActivityRemoteSource
): IActivityRepository {
    
    override suspend fun getActivities() {
    }
    
    override suspend fun joinActivity() {
    }
    
    override suspend fun claimActivityPoints() {
    }
}

interface IActivityRepository {
    suspend fun getActivities()
    suspend fun joinActivity()
    suspend fun claimActivityPoints()
}