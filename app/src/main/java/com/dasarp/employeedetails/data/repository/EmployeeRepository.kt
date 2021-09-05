package com.dasarp.employeedetails.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dasarp.employeedetails.data.api.ApiClient
import com.dasarp.employeedetails.data.model.EmployeeModel
import com.dasarp.employeedetails.utils.AppResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class EmployeeRepository : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    fun getEmployeeList() : LiveData<AppResource<List<EmployeeModel>>>{
        val employeeMutableLiveData : MutableLiveData<AppResource<List<EmployeeModel>>> = MutableLiveData()
        employeeMutableLiveData.postValue(AppResource.loading())
        launch {
            ApiClient.instance.getEmployeeList().enqueue(object : Callback<List<EmployeeModel>>{
                override fun onResponse(
                    call: Call<List<EmployeeModel>>,
                    response: Response<List<EmployeeModel>>
                ) {
                    if(response.isSuccessful){
                        employeeMutableLiveData.postValue(AppResource.success(response.body()!!))
                    }else{
                        employeeMutableLiveData.postValue(AppResource.error(null))
                    }
                }
                override fun onFailure(call: Call<List<EmployeeModel>>, t: Throwable) {
                    employeeMutableLiveData.postValue(AppResource.error(t.message))
                }
            })
        }
        return employeeMutableLiveData
    }

    fun getEmployee(empId : Int) : LiveData<AppResource<EmployeeModel>>{
        val employeeMutableLiveData : MutableLiveData<AppResource<EmployeeModel>> = MutableLiveData()
        employeeMutableLiveData.postValue(AppResource.loading())
        launch {
            ApiClient.instance.getEmployee(empId).enqueue(object : Callback<EmployeeModel>{
                override fun onResponse(
                    call: Call<EmployeeModel>,
                    response: Response<EmployeeModel>
                ) {
                    if(response.isSuccessful){
                        println("employee -->${response.body()}")
                        employeeMutableLiveData.postValue(AppResource.success(response.body()!!))
                    }else{
                        employeeMutableLiveData.postValue(AppResource.error(null))
                    }
                }
                override fun onFailure(call: Call<EmployeeModel>, t: Throwable) {
                    employeeMutableLiveData.postValue(AppResource.error(t.message))
                }

            })
        }
        return employeeMutableLiveData
    }

}