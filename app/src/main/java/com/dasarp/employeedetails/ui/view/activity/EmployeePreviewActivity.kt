package com.dasarp.employeedetails.ui.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dasarp.employeedetails.R
import com.dasarp.employeedetails.databinding.EmployeePreviewLayoutBinding
import com.dasarp.employeedetails.ui.viewmodel.EmployeeViewModel
import com.dasarp.employeedetails.utils.LoadingStatus
import com.dasarp.employeedetails.utils.NetworkUtil
import com.google.android.material.snackbar.Snackbar

class EmployeePreviewActivity : AppCompatActivity() {

    private lateinit var employeeBinding : EmployeePreviewLayoutBinding
    private lateinit var empViewModel : EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        employeeBinding = EmployeePreviewLayoutBinding.inflate(LayoutInflater.from(this))
        setContentView(employeeBinding.root)
        initUI()
    }

    private fun initUI(){
        empViewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]
       if(NetworkUtil(this).isNetworkConnected()){
           empViewModel.getEmployee(intent?.getIntExtra("employeeId",0)!!).observe(this,{
               when(it.status){
                   LoadingStatus.LOADING -> {
                       employeeBinding.progressBar.visibility = View.VISIBLE
                       employeeBinding.dataLayout.visibility = View.GONE
                   }
                   LoadingStatus.SUCCESS -> {
                       if(it.data != null){
                           employeeBinding.employeeModel = it.data
                       }
                       employeeBinding.progressBar.visibility = View.GONE
                       employeeBinding.dataLayout.visibility = View.VISIBLE
                   }
                   LoadingStatus.ERROR -> {
                       showSnackBarToast(getString(R.string.toast_something_went_wrong))
                   }
               }
           })
       }else{
           showSnackBarToast(getString(R.string.toast_network_error))
       }
    }

    private fun showSnackBarToast(msg : String){
        Snackbar.make(employeeBinding.parentLayout,msg, Snackbar.LENGTH_SHORT).show()
    }
}