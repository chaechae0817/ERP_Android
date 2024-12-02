package com.example.erp_qr.data

data class VacationDTO(
    var id: Long,
    var employeeId: Long,
    var name: String,
    var position: String,
    var department: String,
    var leaveItemName: String,
    var startDate: String,
    var endDate: String,
    var reason: String
)
