package com.example.erp_qr.data

data class NotificationDTO(
    var id: Long,
    var isRead: Boolean,
    var type: String,
    var typeId: String,
    var createdAt: String,
    var employeeId: Long
){
    // 타입에 따라 변환된 텍스트를 제공
    val displayType: String
        get() = when (type) {
            "vacation_pending" -> "휴가 신청 완료"
            "vacation_approve" -> "휴가 승인"
            "attendance_checkIn" -> "출근"
            "attendance_checkout" -> "퇴근"
            else -> "알림"
        }

    // 메시지 가공 (필요하면 메시지 재구성)
    val displayMessage: String
        get() = when(type){
            "vacation_pending" -> "휴가 신청이 완료되었습니다."
            "vacation_approve" -> "휴가 승인이 완료되었습니다."
            "attendance_checkIn" -> "출근이 완료되었습니다."
            "attendance_checkout" -> "퇴근이 완료되었습니다."
            else -> "새로운 알림 도착"
        }
}