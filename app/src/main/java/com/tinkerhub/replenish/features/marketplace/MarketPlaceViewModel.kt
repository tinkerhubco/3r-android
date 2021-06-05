package com.tinkerhub.replenish.features.marketplace

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkerhub.replenish.data.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MarketPlaceViewModel @Inject constructor() : ViewModel() {
    val user = MutableLiveData<User>()
}