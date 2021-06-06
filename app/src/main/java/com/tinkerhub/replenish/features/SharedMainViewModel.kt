package com.tinkerhub.replenish.features

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinkerhub.replenish.data.models.User
import com.tinkerhub.replenish.sources.user.IUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedMainViewModel @Inject constructor(
    private val userRepository: IUserRepository
) : ViewModel() {
    
    val user = MutableLiveData<User>()
    
    init {
        viewModelScope.launch {
            user.value = User.getDefault()
            getUserProfile()
        }
    }
    
    suspend fun getUserProfile() {
        user.value = userRepository.getUserProfile() ?: userRepository.login()
    }
}