package com.example.erp_qr.salary

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.erp_qr.Retrofit.RetrofitApplication
import com.example.erp_qr.data.SalaryDTO
import com.example.erp_qr.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class SalaryViewModel @Inject constructor(private val loginRepository: LoginRepository): ViewModel() {

    private val _salaryData = MutableLiveData<SalaryDTO>()
    val salaryData: MutableLiveData<SalaryDTO> get() = _salaryData
    private val _allowanceVisible = MutableLiveData<Boolean>()
    val allowanceVisible: MutableLiveData<Boolean> get() = _allowanceVisible
    private val _deductionVisible = MutableLiveData<Boolean>()
    val deductionVisible: MutableLiveData<Boolean> get() = _deductionVisible

    private val _currentMonth = MutableLiveData<String>()
    val currentMonth: MutableLiveData<String> get() = _currentMonth



    init {
        val data = loginRepository.getLoginData()
        val employeeId = data["employeeId"] ?: "No ID Found"
        val todayMonth = getMonth()
        _currentMonth.value = todayMonth
        loadSalaryData(employeeId, todayMonth)
        _allowanceVisible.value = true
        _deductionVisible.value = false

    }



    private fun loadSalaryData(employeeId: String, month: String) {
        RetrofitApplication.networkService.getSalaryList(employeeId, month).clone()
            ?.enqueue(object : Callback<SalaryDTO> {
                override fun onResponse(call: Call<SalaryDTO>, response: Response<SalaryDTO>) {
                    if (response.isSuccessful){
                        _salaryData.value = response.body()!!
                    }else{
                        Log.d(TAG, "currentMonth : $_currentMonth")
                        Log.d(TAG, "Response Failed: Code = ${response.code()}, Message = ${response.message()}, ErrorBody = ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<SalaryDTO>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
    }

    fun showAllowances(){
        _allowanceVisible.value = true
        _deductionVisible.value = false
    }
    fun showDeductions(){
        _allowanceVisible.value = false
        _deductionVisible.value = true
    }
    

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMonth(): String {
        val currentDate = LocalDate.now() // 오늘 날짜
        val year = currentDate.year
        val month = currentDate.monthValue.toString().padStart(2, '0') // 1월은 "01" 형식으로
        return "$year-$month"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun changeMonth(increment: Int) {
        val currentDate = LocalDate.parse("${_currentMonth.value}-01")
        val newDate = currentDate.plusMonths(increment.toLong()) // Increment (+1) or Decrement (-1)
        _currentMonth.value = "${newDate.year}-${newDate.monthValue.toString().padStart(2, '0')}"
        val employeeId = loginRepository.getLoginData()["employeeId"] ?: "No ID Found"
        loadSalaryData(employeeId, _currentMonth.value!!)
    }

    
    companion object{
        private const val TAG = "SalaryViewModel"
    }

}

