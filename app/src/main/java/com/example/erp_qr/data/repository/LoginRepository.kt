package com.example.erp_qr.data.repository

import android.content.SharedPreferences
import javax.inject.Inject

class LoginRepository @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun saveLoginData(employeeId: String,employeeNumber: String, email: String) {
        val editor = sharedPreferences.edit()
        editor.putString("employeeId", employeeId)
        editor.putString("employeeNumber", employeeNumber)
        editor.putString("email", email)
        editor.apply()
    }

    fun getLoginData(): Map<String,String> {
        val employeeId = sharedPreferences.getString("employeeId", null)
        val employeeNumber = sharedPreferences.getString("employeeNumber", null)
        val email = sharedPreferences.getString("email", null)
        return mapOf(
            "employeeId" to (employeeId ?: ""),
            "employeeNumber" to (employeeNumber ?: ""),
            "email" to (email ?: "")
        )
    }
}