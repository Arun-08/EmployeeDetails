package com.dasarp.employeedetails.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dasarp.employeedetails.data.model.EmployeeModel
import com.dasarp.employeedetails.databinding.EmployeeItemLayoutBinding

class EmployeeAdapter : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    var employeeList : List<EmployeeModel> = arrayListOf()
    lateinit var onItemListener : EmployeeAdapter.onItemClickListener

    inner class EmployeeViewHolder(val employeeItemBinding: EmployeeItemLayoutBinding) : RecyclerView.ViewHolder(employeeItemBinding.root){
        fun setData(itemModel : EmployeeModel){
            employeeItemBinding.employeeModel = itemModel
            employeeItemBinding.itemLayout.setOnClickListener {
                onItemListener.onEmployeeDetail(itemModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding : EmployeeItemLayoutBinding = EmployeeItemLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.setData(employeeList[position])
    }

    override fun getItemCount(): Int = employeeList.size

    interface onItemClickListener{
        fun onEmployeeDetail(employeeModel : EmployeeModel)
    }

}