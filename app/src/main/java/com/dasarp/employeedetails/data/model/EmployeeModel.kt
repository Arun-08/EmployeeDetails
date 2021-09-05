package com.dasarp.employeedetails.data.model

import com.google.gson.annotations.SerializedName

data class EmployeeModel(
    @SerializedName("id")
    var id : Int = 0,
    @SerializedName("name")
    var name : String = "",
    @SerializedName("username")
    var userName : String = "",
    @SerializedName("email")
    var email : String = "",
    @SerializedName("address")
    var addressModel : EmployeeAddress,
    @SerializedName("phone")
    var phone : String = "",
    @SerializedName("website")
    var website : String = "",
    @SerializedName("company")
    var companyModel: EmployeeCompany
)

data class EmployeeAddress(
    @SerializedName("street")
    var street : String = "",
    @SerializedName("suite")
    var suite : String = "",
    @SerializedName("city")
    var city : String = "",
    @SerializedName("zipcode")
    var zipcode : String = "",
    @SerializedName("geo")
    var geoModel : EmployeeGeoLocation
)

data class EmployeeGeoLocation(
    @SerializedName("lat")
    var latitude : String = "",
    @SerializedName("lng")
    var longitude : String = ""
)

data class EmployeeCompany(
    @SerializedName("name")
    var name : String = "",
    @SerializedName("catchPhrase")
    var catchPhrase : String = "",
    @SerializedName("bs")
    var bs : String = ""
)