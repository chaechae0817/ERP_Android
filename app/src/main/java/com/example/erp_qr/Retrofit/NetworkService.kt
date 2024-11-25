package com.example.erp_qr.Retrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NetworkService {
    @FormUrlEncoded
    @POST("/android/login")
    fun login(@Field("employeeNumber") employeeNumber: String,
              @Field("email") email: String): Call<Map<String,Any>>
}