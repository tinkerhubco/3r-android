package com.tinkerhub.replenish.features.profile.rewards.tabs

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RewardsTabViewModel @Inject constructor() : ViewModel() {
    
    var isRedeemedTab = false
}