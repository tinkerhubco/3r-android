package com.tinkerhub.replenish.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.data.models.RewardItem
import com.tinkerhub.replenish.databinding.ItemRewardBinding

class RewardItemAdapter(
    private val listener: RewardItemListener? = null,
    private val isRedeemed: Boolean = false
) : ListAdapter<RewardItem, RewardItemAdapter.RewardItemViewHolder>(
    object : DiffUtil.ItemCallback<RewardItem>() {
        override fun areItemsTheSame(
            oldItem: RewardItem,
            newItem: RewardItem
        ): Boolean {
            return oldItem == newItem
        }
        
        override fun areContentsTheSame(
            oldItem: RewardItem,
            newItem: RewardItem
        ): Boolean {
            return oldItem._id == newItem._id
                && oldItem.title == newItem.title
                && oldItem.coverPhotoUrl == newItem.coverPhotoUrl
                && oldItem.organizer == newItem.organizer
        }
    }
) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardItemViewHolder {
        val binding = DataBindingUtil.inflate<ItemRewardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_reward,
            parent,
            false
        )
        
        return RewardItemViewHolder(binding)
    }
    
    override fun getItemCount() = currentList.size
    
    override fun onBindViewHolder(holder: RewardItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
    
    inner class RewardItemViewHolder(
        private val binding: ItemRewardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: RewardItem) {
            binding.rewardItem = item
            binding.isRedeemed = isRedeemed
            binding.root.setOnClickListener {
                if (item._id.isEmpty() || isRedeemed) return@setOnClickListener
                listener?.onRewardItemClicked(item)
            }
        }
    }
    
    interface RewardItemListener {
        fun onRewardItemClicked(item: RewardItem)
    }
}
