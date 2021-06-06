package com.tinkerhub.replenish.features.explore

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.common.utils.autoCleared
import com.tinkerhub.replenish.data.adapters.EventItemAdapter
import com.tinkerhub.replenish.data.models.EventItem
import com.tinkerhub.replenish.data.models.User
import com.tinkerhub.replenish.databinding.FragmentExploreBinding
import com.tinkerhub.replenish.features.SharedMainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExploreFragment :
    Fragment(),
    EventItemAdapter.EventItemListener {
    
    private var binding: FragmentExploreBinding by autoCleared()
    private val viewModel: ExploreViewModel by viewModels()
    private val sharedMainViewModel: SharedMainViewModel by activityViewModels()
    private lateinit var eventItemAdapter: EventItemAdapter
    private lateinit var activityItemAdapter: EventItemAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_explore,
            container,
            false
        )
        eventItemAdapter = EventItemAdapter(this, isActivity = false)
        activityItemAdapter = EventItemAdapter(this, isActivity = true)
        binding.sharedMainViewModel = sharedMainViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.recyclerViewUpcomingEvents.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = eventItemAdapter
        }
        
        binding.recyclerViewTrendingActivities.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = activityItemAdapter
        }
        
        binding.cardViewUserProfile.setOnClickListener {
            sharedMainViewModel.user.value?.let {
                if (it != User.getDefault()) {
                    findNavController().navigate(
                        ExploreFragmentDirections
                            .actionExploreFragmentToUserProfileFragment()
                    )
                }
            }
        }
        
        binding.layoutSwipeToRefresh.setOnRefreshListener {
            viewModel.viewModelScope.launch {
                viewModel.loadActivities()
                binding.layoutSwipeToRefresh.isRefreshing = false
            }
        }
        
        viewModel.eventsList.observe(viewLifecycleOwner) {
            eventItemAdapter.submitList(it)
        }
        
        viewModel.activitiesList.observe(viewLifecycleOwner) {
            activityItemAdapter.submitList(it)
        }
    }
    
    override fun onEventItemClicked(item: EventItem) {
        findNavController().navigate(
            ExploreFragmentDirections.actionExploreFragmentToEventItemDisplayFragment(
                item
            )
        )
    }
}