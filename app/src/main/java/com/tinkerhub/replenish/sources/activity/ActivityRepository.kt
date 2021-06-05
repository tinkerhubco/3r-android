package com.tinkerhub.replenish.sources.activity

import com.tinkerhub.replenish.data.models.EventItem
import com.tinkerhub.replenish.data.models.User
import com.tinkerhub.replenish.network.Result
import com.tinkerhub.replenish.network.responses.ActivitiesResponse

class ActivityRepository(
    private val remoteSource: IActivityRemoteSource
) : IActivityRepository {
    
    override suspend fun getActivities(): ActivitiesResponse {
        val activityListResult = remoteSource.requestGetActivities()
        val defaultActivitiesResponse = ActivitiesResponse(
            upcoming = emptyList(),
            trending = emptyList()
        )
        
        return if (activityListResult is Result.Success) {
            activityListResult.data ?: defaultActivitiesResponse
        } else {
            defaultActivitiesResponse
        }
    }
    
    override suspend fun getActivity(activityId: String): EventItem? {
        val activityResult = remoteSource.requestGetActivity(activityId)
        
        return if (activityResult is Result.Success) {
            activityResult.data
        } else null
    }
    
    override suspend fun joinActivity() {
    }
    
    override suspend fun claimActivityPoints(
        activityId: String,
        userId: String,
        voucherId: String
    ): User? {
        val activityPointsResult = remoteSource.requestClaimActivityPoints(
            activityId,
            userId,
            voucherId
        )
        
        return if (activityPointsResult is Result.Success) {
            activityPointsResult.data
        } else null
    }
}

interface IActivityRepository {
    suspend fun getActivities(): ActivitiesResponse
    suspend fun getActivity(activityId: String): EventItem?
    suspend fun joinActivity()
    suspend fun claimActivityPoints(
        activityId: String,
        userId: String,
        voucherId: String
    ): User?
}