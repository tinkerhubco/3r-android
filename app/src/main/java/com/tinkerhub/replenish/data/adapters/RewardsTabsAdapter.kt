package com.tinkerhub.replenish.data.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tinkerhub.replenish.features.profile.rewards.tabs.RewardsTabFragment

class RewardsTabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    
    override fun getItemCount(): Int = 2
    
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RewardsTabFragment.getFragment(isRedeemedTab = false)
            else -> RewardsTabFragment.getFragment(isRedeemedTab = true)
        }
    }
}