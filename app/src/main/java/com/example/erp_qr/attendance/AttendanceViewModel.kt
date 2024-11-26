package com.example.erp_qr.attendance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.erp_qr.Retrofit.RetrofitApplication

class AttendanceViewModel : ViewModel(){

    private val _attendanceData = MutableLiveData<List<Attendance>>()
    val attendanceData: LiveData<List<Attendance>> get() = _attendanceData

    init {
        loadAttendanceData()
    }

    private fun loadAttendanceData() {
        RetrofitApplication.networkService.getAttendanceList()
        val data = listOf(
            Attendance("2024-12-06", "09:08 AM", "06:05 PM", "08:13"),
            Attendance("2024-12-07", "09:08 AM", "06:05 PM", "08:13"),
            Attendance("2024-12-08", "09:08 AM", "06:05 PM", "08:13")
        )
        _attendanceData.value = data
    }





}

data class Attendance(
    val date: String,
    val checkInTime: String,
    val checkOutTime: String,
    val totalHours: String
)