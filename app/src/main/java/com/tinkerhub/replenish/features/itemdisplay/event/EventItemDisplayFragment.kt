package com.tinkerhub.replenish.features.itemdisplay.event

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
                it.hasJoined && it.attemptsCount > 0
                    && it.maxAttemptsCount != it.attemptsCount -> {
                    binding.buttonItemAction.text =
                        getString(R.string.button_action_claim_more_points)
                    binding.textviewItemActionLabel.text =
                        getString(R.string.label_action_claim_more_points)
                }
                it.hasJoined && it.attemptsCount == 0 -> {
                    binding.buttonItemAction.text =
                        getString(R.string.button_action_joined)
                    binding.textviewItemActionLabel.text =
                        getString(R.string.label_action_joined)
                }
                it.hasJoined && it.maxAttemptsCount == it.attemptsCount -> {
                    binding.buttonItemAction.text =
                        getString(R.string.button_action_max_points)
                    binding.textviewItemActionLabel.text =
                        getString(R.string.label_action_max_points)
                }
                else -> {
                    binding.buttonItemAction.text = getString(R.string.button_action_join)
                    binding.textviewItemActionLabel.text = getString(R.string.label_action_join)
                }
            }
        }
        
        viewModel.buttonActionClicked.observe(viewLifecycleOwner, EventObserver {
            if (it !is EventItem) return@EventObserver
            if (it.hasJoined) {
                findNavController().navigate(
                    EventItemDisplayFragmentDirections
                        .actionEventItemDisplayFragmentToAcknowledgementDialog(
                            imageUrlArg = getString(R.string.qr_url_link, it.title),
                            titleTextArg = it.title,
                            subtitleTextArg = getString(R.string.description_show_qr_code),
                            buttonActionTextArg = getString(R.string.button_action_see_rewards)
                        )
                )
            } else {
                Toast.makeText(context, "Join ${it.title} now!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}