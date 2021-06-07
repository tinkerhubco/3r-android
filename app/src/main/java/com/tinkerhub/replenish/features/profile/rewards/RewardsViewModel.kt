package com.tinkerhub.replenish.features.profile.rewards

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinkerhub.replenish.common.utils.Event
import com.tinkerhub.replenish.data.models.RewardItem
import com.tinkerhub.replenish.data.models.User
import com.tinkerhub.replenish.sources.rewards.IRewardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RewardsViewModel @Inject constructor(
    private val rewardRepository: IRewardRepository
) : ViewModel() {
    
    val availableRewardsList = MutableLiveData<ArrayList<RewardItem>>()
    val redeemedRewardsList = MutableLiveData<ArrayList<RewardItem>>()
    val selectedRewardItem = MutableLiveData<Event<RewardItem>>()
    val user = MutableLiveData<User>()
    
    init {
        viewModelScope.launch {
            loadSkeleton()
            loadRewards(redeemed = false)
            loadRewards(redeemed = true)
        }
    }
    
    private fun loadSkeleton() {
        val mockAvailableRewardsList = arrayListOf<RewardItem>()
        val mockRedeemedRewardsList = arrayListOf<RewardItem>()
        
        for (i in 1..6) {
            val mockItem = RewardItem.getDefault()
            
            mockAvailableRewardsList.add(mockItem)
            mockRedeemedRewardsList.add(mockItem)
        }
        
        availableRewardsList.value = mockAvailableRewardsList
        redeemedRewardsList.value = mockRedeemedRewardsList
    }
    
    suspend fun loadRewards(redeemed: Boolean) {
        if (redeemed) {
            redeemedRewardsList.value = ArrayList(rewardRepository.getRewards("redeemed"))
        } else {
            availableRewardsList.value = ArrayList(rewardRepository.getRewards(null))
        }
    }
}