package com.example.erp_qr.Retrofit

import com.example.erp_qr.data.AttendanceRecord
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkService {
    @FormUrlEncoded
    @POST("/android/login")
    fun login(@Field("employeeNumber") employeeNumber: String,
              @Field("email") email: String): Call<Map<String,Any>>

    @GET("/android/list/{employeeId}/{month}")
    fun getAttendanceList(@Path("employeeId") employeeId: String,@Path("month") month: String): Call<List<AttendanceRecord>>



}