package com.tinkerhub.replenish.features.itemdisplay.event

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.common.utils.EventObserver
import com.tinkerhub.replenish.data.models.EventItem
import com.tinkerhub.replenish.features.itemdisplay.ItemDisplayFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventItemDisplayFragment : ItemDisplayFragment() {
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel.itemDisplay.observe(viewLifecycleOwner) {
            if (it !is EventItem) return@observe
            when {
                it._id.isEmpty() -> Unit
                it.hasJoined && it.attemptsCount > 0
                    && it.maxAttemptsCount != it.attemptsCount -> {
                    binding.buttonText =
                        getString(R.string.button_action_claim_more_points)
                    binding.labelText =
                        getString(R.string.label_action_claim_more_points)
                }
                it.hasJoined && it.attemptsCount == 0 -> {
                    binding.buttonText =
                        getString(R.string.button_action_joined)
                    binding.labelText =
                        getString(R.string.label_action_joined)
                }
                it.hasJoined && it.maxAttemptsCount == it.attemptsCount -> {
                    binding.buttonText =
                        getString(R.string.button_action_max_points)
                    binding.labelText =
                        getString(R.string.label_action_max_points)
                }
                else -> {
                    binding.buttonText = getString(R.string.button_action_join)
                    binding.labelText = getString(R.string.label_action_join)
                }
            }
    
            binding.showButtonLoading = it._id.isEmpty()
        }
        
        viewModel.buttonActionClicked.observe(viewLifecycleOwner, EventObserver {
            if (it !is EventItem) return@EventObserver
            if (binding.showButtonLoading) return@EventObserver

            if (it.hasJoined) {
                findNavController().navigate(
                    EventItemDisplayFragmentDirections
                        .actionEventItemDisplayFragmentToRewardSelectorFragment(it._id)
                )
            } else {
                binding.showButtonLoading = true
                viewModel.joinEvent(it)
            }
        })
    }
}