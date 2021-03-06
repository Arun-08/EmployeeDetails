package com.dasarp.employeedetails.data.api

import com.dasarp.employeedetails.data.model.EmployeeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    fun getEmployeeList() : Call<List<EmployeeModel>>

    @GET("users/{empId}")
    fun getEmployee(@Path("empId")id : Int) : Call<EmployeeModel>

}