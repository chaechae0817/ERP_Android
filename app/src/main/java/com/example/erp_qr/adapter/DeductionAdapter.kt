package com.example.erp_qr.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.erp_qr.databinding.ItemDeductionBinding

class DeductionAdapter : ListAdapter<Map.Entry<String, Double>, DeductionAdapter.DeductionViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeductionViewHolder {
        val binding = ItemDeductionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeductionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeductionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DeductionViewHolder(private val binding: ItemDeductionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: Map.Entry<String, Double>) {
            binding.deduction = entry // XML에 바인딩
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
