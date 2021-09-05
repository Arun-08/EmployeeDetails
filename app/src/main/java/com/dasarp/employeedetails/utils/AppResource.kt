package com.dasarp.employeedetails.utils

data class AppResource<out T>(val status: LoadingStatus,val data : T?,val msg : String?){
    companion object{
        fun <T> success(data : T) : AppResource<T> = AppResource(LoadingStatus.SUCCESS,data,null)
        fun <T> error(msg : String?) : AppResource<T> = AppResource(LoadingStatus.ERROR,null,msg)
        fun <T> loading() : AppResource<T> = AppResource(LoadingStatus.LOADING,null,null)
    }
}