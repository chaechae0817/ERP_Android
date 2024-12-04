package com.example.erp_qr.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.erp_qr.data.AttendanceRecordDTO
import com.example.erp_qr.databinding.ItemAttendanceBinding

class AttendanceAdapter : ListAdapter<AttendanceRecordDTO, AttendanceAdapter.AttendanceViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        val binding = ItemAttendanceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttendanceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        holder.bind(getItem(position)) // DataBinding 활용
    }

    class AttendanceViewHolder(private val binding: ItemAttendanceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(attendance: AttendanceRecordDTO) {
            binding.attendance = attendance // XML의 변수에 직접 바인딩
            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<AttendanceRecordDTO>() {
        override fun areItemsTheSame(oldItem: AttendanceRecordDTO, newItem: AttendanceRecordDTO): Boolean {
            return oldItem.recordId == newItem.recordId
        }

        override fun areContentsTheSame(oldItem: AttendanceRecordDTO, newItem: AttendanceRecordDTO): Boolean {
            return oldItem == newItem
        }
    }
}
