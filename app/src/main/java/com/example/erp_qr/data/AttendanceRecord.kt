package com.example.erp_qr.data

data class AttendanceRecord(
    var id: String,
    var employeeName: String,
    var employeeId: String,
    var date: String,
    var checkInTime: String,
    var checkOutTime: String,
    var totalHours: String
)
