package com.dasarp.employeedetails.data.api

import com.dasarp.employeedetails.data.model.EmployeeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    fun getEmployeeList() : Call<List<EmployeeModel>>

    @GET("users/empId")
    fun getCoursesList(@Path("empId")id : Int) : Call<List<EmployeeModel>>

}