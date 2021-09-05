package com.dasarp.employeedetails.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dasarp.employeedetails.R
import com.dasarp.employeedetails.data.model.EmployeeAddress
import com.dasarp.employeedetails.data.model.EmployeeModel
import com.dasarp.employeedetails.databinding.ActivityMainBinding
import com.dasarp.employeedetails.ui.view.adapter.EmployeeAdapter
import com.dasarp.employeedetails.ui.view.widget.MarginItemDecoration
import com.dasarp.employeedetails.ui.viewmodel.EmployeeViewModel
import com.dasarp.employeedetails.utils.LoadingStatus
import com.dasarp.employeedetails.utils.NetworkUtil
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var activityBinding : ActivityMainBinding
    private lateinit var empViewModel : EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(activityBinding.root)
        initUI()
    }

    private fun initUI(){
        empViewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]
        val empAdapter = EmployeeAdapter()
        empAdapter.onItemListener = object : EmployeeAdapter.onItemClickListener{
            override fun onEmployeeDetail(employeeModel: EmployeeModel) {
                startActivity(Intent(this@MainActivity,EmployeePreviewActivity::class.java)
                    .putExtra("employeeId",employeeModel.id))
            }
        }
        if(NetworkUtil(this).isNetworkConnected()){
            empViewModel.getEmployeeList().observe(this,{
                when(it.status){
                    LoadingStatus.LOADING-> {
                        activityBinding.progressBar.visibility = View.VISIBLE
                        activityBinding.recyclerview.visibility = View.GONE
                    }
                    LoadingStatus.SUCCESS-> {
                        if(it.data != null){
                            empAdapter.employeeList = it.data
                            empAdapter.notifyDataSetChanged()
                        }
                        activityBinding.progressBar.visibility = View.GONE
                        activityBinding.recyclerview.visibility = View.VISIBLE
                    }
                    LoadingStatus.ERROR-> {
                        showSnackBarToast(getString(R.string.toast_something_went_wrong))
                    }
                }
            })
        }else{
            showSnackBarToast(getString(R.string.toast_network_error))
        }
        activityBinding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_10),"VERTICAL"))
            adapter = empAdapter
        }
    }


    private fun showSnackBarToast(msg : String){
        Snackbar.make(activityBinding.parentLayout,msg,Snackbar.LENGTH_SHORT).show()
    }
}