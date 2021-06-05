package com.tinkerhub.replenish.sources.rewards

import com.tinkerhub.replenish.data.models.RewardItem
import com.tinkerhub.replenish.network.Result

class RewardRepository(
    private val remoteSource: IRewardRemoteSource
) : IRewardRepository {
    
    override suspend fun getRewards(type: String?): List<RewardItem> {
        val rewardsListResult = remoteSource.requestGetRewards(type)
        
        return if (rewardsListResult is Result.Success) {
            rewardsListResult.data ?: emptyList()
        } else {
            emptyList()
        }
    }
}

interface IRewardRepository {
    suspend fun getRewards(type: String?): List<RewardItem>
}