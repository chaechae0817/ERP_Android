package com.example.erp_qr.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.erp_qr.Retrofit.RetrofitApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel(){

    var employeeNumber: MutableLiveData<String> = MutableLiveData("")
    var email: MutableLiveData<String> = MutableLiveData("")
    var loginSuccess: MutableLiveData<Boolean> = MutableLiveData(false)
    var errorMessage: MutableLiveData<String> = MutableLiveData("")


    fun Loginsucess(){
        RetrofitApplication.networkService.login(employeeNumber.value.toString(),email.value.toString()).clone().enqueue(object :
            Callback<Map<String, Any>> {
            override fun onResponse(call: Call<Map<String, Any>>, response: Response<Map<String, Any>>) {
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val success = responseBody["success"] as? Boolean ?: false
                        loginSuccess.value = success

                        if (!success) {
                            errorMessage.value = "Login failed. Check credentials."
                        }
                    } else {
                        errorMessage.value = "Server response is empty."
                    }
                } else {
                    errorMessage.value = "Server error: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<Map<String, Any>>, t: Throwable) {
                errorMessage.value = "Network error: ${t.message}"
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })


    }


    companion object{
        private const val TAG = "LoginViewModel"
    }

}