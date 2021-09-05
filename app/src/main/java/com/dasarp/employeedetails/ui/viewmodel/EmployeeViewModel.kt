package com.dasarp.employeedetails.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dasarp.employeedetails.data.model.EmployeeModel
import com.dasarp.employeedetails.data.repository.EmployeeRepository
import com.dasarp.employeedetails.utils.AppResource

class EmployeeViewModel : ViewModel() {

    private val empRepo = EmployeeRepository()

    fun getEmployeeList() : LiveData<AppResource<List<EmployeeModel>>> = empRepo.getEmployeeList()
    fun getEmployee(empId : Int) : LiveData<AppResource<EmployeeModel>> = empRepo.getEmployee(empId)

}