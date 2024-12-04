package com.example.erp_qr.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.erp_qr.databinding.ItemAllowanceBinding

class AllowanceAdapter : ListAdapter<Map.Entry<String, Double>, AllowanceAdapter.AllowanceViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllowanceViewHolder {
        val binding = ItemAllowanceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllowanceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllowanceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AllowanceViewHolder(private val binding: ItemAllowanceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: Map.Entry<String, Double>) {
            binding.allowance = entry // XML에 바인딩
            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Map.Entry<String, Double>>() {
        override fun areItemsTheSame(oldItem: Map.Entry<String, Double>, newItem: Map.Entry<String, Double>): Boolean {
            return oldItem.key == newItem.key
        }

        override fun areContentsTheSame(oldItem: Map.Entry<String, Double>, newItem: Map.Entry<String, Double>): Boolean {
            return oldItem == newItem
        }
    }
}
