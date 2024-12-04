package com.example.erp_qr.data

data class VacationDTO(
    val id: Int,
    val employeeId: Int,
    val name: String,
    val position: String,
    val department: String,
    val leaveItemName: String, // 휴가 유형
    val startDate: String,     // 시작일
    val endDate: String,       // 마감일
    val reason: String,        // 사유
    val status: String         // 처리 상태 (승인, 보류, 거절)
)