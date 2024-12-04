package com.example.erp_qr.vaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.erp_qr.data.VacationDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VacationViewModel @Inject constructor() : ViewModel() {

    private val _vacationData = MutableLiveData<List<VacationDTO>>()
    val vacationData: LiveData<List<VacationDTO>> get() = _vacationData

    init {
        loadVacationData()
    }

    private fun loadVacationData() {
        val sampleData = listOf(
            VacationDTO(1, 11, "홍길동", "이사", "인사부", "병가", "11-06", "11-09", "아퍼", "승인"),
            VacationDTO(2, 11, "홍길동", "이사", "인사부", "병가", "10-27", "10-29", "감기", "보류"),
            VacationDTO(3, 11, "홍길동", "이사", "인사부", "연가", "11-24", "11-28", "휴가", "거절")
        )
        _vacationData.value = sampleData
    }
}
