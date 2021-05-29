package com.tinkerhub.replenish.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.data.models.RewardItem
import com.tinkerhub.replenish.databinding.ItemRewardBinding

class RewardItemAdapter(
    private val listener: RewardItemListener? = null
) : RecyclerView.Adapter<RewardItemAdapter.RewardItemViewHolder>() {
    
    private val rewardItemList = arrayListOf<RewardItem>()
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardItemViewHolder {
        val binding = DataBindingUtil.inflate<ItemRewardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_reward,
            parent,
            false
        )
        
        return RewardItemViewHolder(binding)
    }
    
    override fun getItemCount() = rewardItemList.size
    
    override fun onBindViewHolder(holder: RewardItemViewHolder, position: Int) {
        holder.itemView.clearAnimation()
        holder.bind(rewardItemList[position])
    }
    
    fun updateList(list: ArrayList<RewardItem>) {
        rewardItemList.clear()
        rewardItemList.addAll(list)
        notifyDataSetChanged()
    }
    
    inner class RewardItemViewHolder(
        private val binding: ItemRewardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: RewardItem) {
            binding.rewardItem = item
            binding.root.setOnClickListener {
                listener?.onRewardItemClicked(item)
            }
        }
    }
    
    interface RewardItemListener {
        fun onRewardItemClicked(item: RewardItem)
    }
}
