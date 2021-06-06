package com.tinkerhub.replenish.sources.rewards

import android.content.Context
import com.tinkerhub.replenish.data.models.RewardItem
import com.tinkerhub.replenish.network.ApiService
import com.tinkerhub.replenish.network.Result
import com.tinkerhub.replenish.network.wrapWithResult
import com.tinkerhub.replenish.sources.BaseRemoteSource
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RewardRemoteSource(
    context: Context,
    private val apiService: ApiService
) : BaseRemoteSource(context), IRewardRemoteSource {
    
    override suspend fun requestGetRewards(type: String?): Result<List<RewardItem>> {
        return try {
            val response = withContext(Dispatchers.IO) {
                apiService.getRewards()
            }
            response.wrapWithResult()
        } catch (exception: CancellationException) {
            Result.Cancelled()
        } catch (exception: Exception) {
            getDefaultErrorResponse()
        }
    }
}

interface IRewardRemoteSource {
    suspend fun requestGetRewards(type: String?): Result<List<RewardItem>>
}