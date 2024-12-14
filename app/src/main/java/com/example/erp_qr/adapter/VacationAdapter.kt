package com.example.erp_qr.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.erp_qr.data.VacationDTO
import com.example.erp_qr.databinding.ItemVacationBinding

class VacationAdapter : ListAdapter<VacationDTO, VacationAdapter.VacationViewHolder>(DiffCallback()) {

    class VacationViewHolder(private val binding: ItemVacationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(vacation: VacationDTO) {
            binding.vacation = vacation
            binding.tvStatus.setTextColor(
                when (vacation.status) {
                    "APPROVED" -> itemView.context.getColor(android.R.color.holo_green_dark)
                    "PENDING" -> itemView.context.getColor(android.R.color.holo_orange_dark)
                    "REJECTED" -> itemView.context.getColor(android.R.color.holo_red_dark)
                    else -> itemView.context.getColor(android.R.color.darker_gray)
                }
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacationViewHolder {
        val binding = ItemVacationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VacationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VacationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<VacationDTO>() {
        override fun areItemsTheSame(oldItem: VacationDTO, newItem: VacationDTO) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: VacationDTO, newItem: VacationDTO) = oldItem == newItem
    }
}