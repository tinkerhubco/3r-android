package com.tinkerhub.replenish.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.data.models.EventItem
import com.tinkerhub.replenish.databinding.ItemActivityBinding
import com.tinkerhub.replenish.databinding.ItemEventBinding

class EventItemAdapter(
    private val listener: EventItemListener? = null,
    private val isActivity: Boolean = false
) : ListAdapter<EventItem, EventItemAdapter.EventItemViewHolder>(
    object : DiffUtil.ItemCallback<EventItem>() {
        override fun areItemsTheSame(
            oldItem: EventItem,
            newItem: EventItem
        ): Boolean {
            return oldItem == newItem
        }
        
        override fun areContentsTheSame(
            oldItem: EventItem,
            newItem: EventItem
        ): Boolean {
            return oldItem._id == newItem._id
                && oldItem.title == newItem.title
                && oldItem.coverPhotoUrl == newItem.coverPhotoUrl
                && oldItem.organizer == newItem.organizer
        }
    }
) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventItemViewHolder {
        val binding =
            if (isActivity) {
                DataBindingUtil.inflate<ItemActivityBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_activity,
                    parent,
                    false
                )
            } else {
                DataBindingUtil.inflate<ItemEventBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_event,
                    parent,
                    false
                )
            }
        
        return EventItemViewHolder(binding)
    }
    
    override fun getItemCount() = currentList.size
    
    override fun onBindViewHolder(holder: EventItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
    
    inner class EventItemViewHolder(
        private val binding: ViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: EventItem) {
            when (binding) {
                is ItemEventBinding -> {
                    binding.eventItem = item
                }
                is ItemActivityBinding -> {
                    binding.eventItem = item
                }
            }
            
            binding.root.setOnClickListener {
                if (item._id.isEmpty()) return@setOnClickListener
                listener?.onEventItemClicked(item)
            }
        }
    }
    
    interface EventItemListener {
        fun onEventItemClicked(item: EventItem)
    }
}
