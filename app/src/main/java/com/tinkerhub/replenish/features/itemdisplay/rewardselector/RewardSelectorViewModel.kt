package com.tinkerhub.replenish.features.itemdisplay.rewardselector

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinkerhub.replenish.data.models.RewardItem
import com.tinkerhub.replenish.sources.activity.IActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RewardSelectorViewModel @Inject constructor(
    private val activityRepository: IActivityRepository
) : ViewModel() {
    
    val rewardsList = MutableLiveData<List<RewardItem>>()
    
    fun loadRewardsList(activityId: String) {
        viewModelScope.launch {
            val mockRewardsList = arrayListOf<RewardItem>()
            for (i in 1..6) {
                val mockItem = RewardItem.getDefault()
                mockRewardsList.add(mockItem)
            }
            rewardsList.value = mockRewardsList
            
            activityRepository.getActivity(activityId)?.run {
                rewardsList.value = vouchers
            }
        }
    }
}