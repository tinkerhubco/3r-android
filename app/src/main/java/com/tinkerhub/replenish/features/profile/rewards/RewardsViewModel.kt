package com.tinkerhub.replenish.features.profile.rewards

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.javafaker.Faker
import com.tinkerhub.replenish.common.utils.Event
import com.tinkerhub.replenish.data.models.RewardItem
import com.tinkerhub.replenish.data.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RewardsViewModel @Inject constructor() : ViewModel() {
    
    val availableRewardsList = MutableLiveData<ArrayList<RewardItem>>()
    val redeemedRewardsList = MutableLiveData<ArrayList<RewardItem>>()
    val selectedRewardItem = MutableLiveData<Event<RewardItem>>()
    val user = MutableLiveData<User>()
    
    init {
        val mockAvailableRewardsList = arrayListOf<RewardItem>()
        val mockRedeemedRewardsList = arrayListOf<RewardItem>()
        val faker = Faker()
        
        for (i in 1..10) {
            val mockItem = RewardItem(
                _id = i,
                title = "${i * 10}% on ${faker.book().title()}",
                mechanics = faker.lorem().sentence(100),
                rewardPoints = i * 5,
                organizer = faker.company().name(),
                coverPhotoUrl = faker.company().logo()
            )
            
            mockAvailableRewardsList.add(mockItem)
            mockRedeemedRewardsList.add(mockItem)
        }
        availableRewardsList.value = mockAvailableRewardsList
        redeemedRewardsList.value = mockRedeemedRewardsList
    }
}