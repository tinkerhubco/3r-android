package com.tinkerhub.replenish.features.profile.rewards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.common.utils.EventObserver
import com.tinkerhub.replenish.common.utils.autoCleared
import com.tinkerhub.replenish.data.adapters.RewardsTabsAdapter
import com.tinkerhub.replenish.databinding.FragmentRewardsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RewardsFragment : Fragment() {
    
    private var binding: FragmentRewardsBinding by autoCleared()
    private val viewModel: RewardsViewModel by activityViewModels()
    private lateinit var rewardsTabsAdapter: RewardsTabsAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_rewards,
            container,
            false
        )
        binding.viewModel = viewModel
        rewardsTabsAdapter = RewardsTabsAdapter(this)
        
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.viewPagerRewardsRedeemed.adapter = rewardsTabsAdapter
        
        binding.tabLayoutRewardsRedeemed.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    binding.viewPagerRewardsRedeemed.currentItem = tab.position
                }
                
                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }
                
                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            }
        )
        
        binding.imageButtonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    
        viewModel.selectedRewardItem.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(
                RewardsFragmentDirections
                    .actionRewardsFragmentToRewardItemDisplayFragment(it)
            )
        })
    }
}