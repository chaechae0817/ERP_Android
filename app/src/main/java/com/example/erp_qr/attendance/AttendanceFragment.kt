package com.example.erp_qr.attendance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.erp_qr.R
import com.example.erp_qr.databinding.FragmentAttendanceBinding

class AttendanceFragment : Fragment() {
    private lateinit var binding: FragmentAttendanceBinding
    private val viewModel: AttendanceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_attendance, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        setupRecyclerView()
        setupCalendar()

        return binding.root
    }

    private fun setupRecyclerView() {
//        val adapter = AttendanceAdapter()
//        binding.attendanceRecyclerView.adapter = adapter
//
//        viewModel.attendanceData.observe(viewLifecycleOwner) { data ->
//            adapter.submitList(data)
//        }
    }

    private fun setupCalendar() {
    }



    companion object {
       const val TAG="AttendanceFragment"
    }
}