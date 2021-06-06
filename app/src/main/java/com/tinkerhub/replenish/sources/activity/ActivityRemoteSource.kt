package com.tinkerhub.replenish.sources.activity

import android.content.Context
import com.tinkerhub.replenish.data.models.EventItem
import com.tinkerhub.replenish.data.models.User
import com.tinkerhub.replenish.network.ApiService
import com.tinkerhub.replenish.network.Result
import com.tinkerhub.replenish.network.responses.ActivitiesResponse
import com.tinkerhub.replenish.network.wrapWithResult
import com.tinkerhub.replenish.sources.BaseRemoteSource
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ActivityRemoteSource(
    context: Context,
    private val apiService: ApiService
) : BaseRemoteSource(context), IActivityRemoteSource {
    
    override suspend fun requestGetActivities(): Result<ActivitiesResponse> {
        return try {
            val response = withContext(Dispatchers.IO) {
                apiService.getAllActivities()
            }
            response.wrapWithResult()
        } catch (exception: CancellationException) {
            Result.Cancelled()
        } catch (exception: Exception) {
            getDefaultErrorResponse()
        }
    }
    
    override suspend fun requestGetActivity(activityId: String): Result<EventItem> {
        return try {
            val response = withContext(Dispatchers.IO) {
                apiService.getActivity(activityId)
            }
            response.wrapWithResult()
        } catch (exception: CancellationException) {
            Result.Cancelled()
        } catch (exception: Exception) {
            getDefaultErrorResponse()
        }
    }
    
    override suspend fun requestJoinActivity(activityId: String): Result<EventItem> {
        return try {
            val response = withContext(Dispatchers.IO) {
                apiService.joinActivity(activityId)
            }
            response.wrapWithResult()
        } catch (exception: CancellationException) {
            Result.Cancelled()
        } catch (exception: Exception) {
            getDefaultErrorResponse()
        }
    }
    
    override suspend fun requestClaimActivityPoints(
        activityId: String,
        userId: String,
        voucherId: String
    ): Result<User> {
        return try {
            val response = withContext(Dispatchers.IO) {
                apiService.claimActivityPoints(activityId, userId, voucherId)
            }
            response.wrapWithResult()
        } catch (exception: CancellationException) {
            Result.Cancelled()
        } catch (exception: Exception) {
            getDefaultErrorResponse()
        }
    }
}

interface IActivityRemoteSource {
    suspend fun requestGetActivities(): Result<ActivitiesResponse>
    suspend fun requestGetActivity(activityId: String): Result<EventItem>
    suspend fun requestJoinActivity(activityId: String): Result<EventItem>
    suspend fun requestClaimActivityPoints(
        activityId: String,
        userId: String,
        voucherId: String
    ): Result<User>
}