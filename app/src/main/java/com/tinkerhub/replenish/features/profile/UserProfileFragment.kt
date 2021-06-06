package com.tinkerhub.replenish.features.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.common.utils.autoCleared
import com.tinkerhub.replenish.databinding.FragmentUserProfileBinding
import com.tinkerhub.replenish.features.SharedMainViewModel
import com.tinkerhub.replenish.features.profile.rewards.RewardsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserProfileFragment : Fragment() {
    
    private var binding: FragmentUserProfileBinding by autoCleared()
    private val rewardsViewModel: RewardsViewModel by activityViewModels()
    private val sharedMainViewModel: SharedMainViewModel by activityViewModels()
    private val viewModel: UserProfileViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_user_profile,
            container,
            false
        )
        binding.sharedMainViewModel = sharedMainViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.imageButtonBack.setOnClickListener {
            findNavController().popBackStack()
        }
        
        binding.textviewRewards.setOnClickListener {
            rewardsViewModel.user.value = viewModel.user.value
            findNavController().navigate(
                UserProfileFragmentDirections.actionUserProfileFragmentToRewardsFragment()
            )
        }
    
        binding.layoutSwipeToRefresh.setOnRefreshListener {
            viewModel.viewModelScope.launch {
                sharedMainViewModel.getUserProfile()
                binding.layoutSwipeToRefresh.isRefreshing = false
            }
        }
    }
}