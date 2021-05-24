package com.tinkerhub.replenish.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.data.models.ActivityItem
import com.tinkerhub.replenish.databinding.ItemActivityBinding

class ActivityItemAdapter(
    private val activityItemList: ArrayList<ActivityItem>,
    private val listener: ActivityItemListener? = null
) : RecyclerView.Adapter<ActivityItemAdapter.ActivityItemViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityItemViewHolder {
        val binding = DataBindingUtil.inflate<ItemActivityBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_activity,
            parent,
            false
        )
        
        return ActivityItemViewHolder(binding)
    }
    
    override fun getItemCount() = activityItemList.size
    
    override fun onBindViewHolder(holder: ActivityItemViewHolder, position: Int) {
        holder.itemView.clearAnimation()
        holder.bind(activityItemList[position])
    }
    
    inner class ActivityItemViewHolder(
        private val binding: ItemActivityBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: ActivityItem) {
            binding.activityItem = item
            binding.root.setOnClickListener {
                listener?.onActivityItemClicked(item)
            }
        }
    }
    
    interface ActivityItemListener {
        fun onActivityItemClicked(item: ActivityItem)
    }
}
