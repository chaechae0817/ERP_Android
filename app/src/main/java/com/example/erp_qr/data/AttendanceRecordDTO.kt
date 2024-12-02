package com.example.erp_qr.data

data class AttendanceRecordDTO(
    var recordId: String,
    var employeeId: String,
    var employeeName: String,
    var date: String,
    var checkInTime: String,
    var checkOutTime: String,
    var attendanceType: String,
    var notes: String,
    var totalWorkHours: String
)
