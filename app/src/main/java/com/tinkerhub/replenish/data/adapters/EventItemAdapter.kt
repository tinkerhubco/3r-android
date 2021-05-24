package com.tinkerhub.replenish.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.data.models.EventItem
import com.tinkerhub.replenish.databinding.ItemEventBinding

class EventItemAdapter(
    private val listener: EventItemListener? = null
) : RecyclerView.Adapter<EventItemAdapter.EventItemViewHolder>() {
    
    private val eventItemList = arrayListOf<EventItem>()
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventItemViewHolder {
        val binding = DataBindingUtil.inflate<ItemEventBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_event,
            parent,
            false
        )
        
        return EventItemViewHolder(binding)
    }
    
    override fun getItemCount() = eventItemList.size
    
    override fun onBindViewHolder(holder: EventItemViewHolder, position: Int) {
        holder.itemView.clearAnimation()
        holder.bind(eventItemList[position])
    }
    
    fun updateList(list: ArrayList<EventItem>) {
        eventItemList.clear()
        eventItemList.addAll(list)
        notifyDataSetChanged()
    }
    
    inner class EventItemViewHolder(
        private val binding: ItemEventBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: EventItem) {
            binding.eventItem = item
            binding.root.setOnClickListener {
                listener?.onEventItemClicked(item)
            }
        }
    }
    
    interface EventItemListener {
        fun onEventItemClicked(item: EventItem)
    }
}
