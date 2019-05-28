package com.neo.goservice.repository.data

import java.io.Serializable


/*
    Login Page
 */
data class Login(
        var StatusCode: String? = "",
        var CompanyList: ArrayList<LoginDetail>? = null,
        var last_time_stamp: String? = "",
        var Token: String = "",
        var Role: String? = ""
) : Serializable

data class LoginDetail(
        var company_id: String? = "",
        var company_name: String? = ""
) : Serializable

data class ReLogin(
        var StatusCode: String? = "",
        var CompanyList: ArrayList<LoginDetail>? = null,
        var last_time_stamp: String? = "",
        var Token: String = "",
        var Role: List<Role>? = null,
        var company_id: String? = "",
        var account_name: String? = ""
) : Serializable


data class Role(
        var role_id: String = "",
        var status: String = "",
        var access_control: String = "",
        var role_name: String = ""
) : Serializable

data class LoginVerify(
        var token_id: String = "",
        var company_id: String = "",
        var account_id: String = "",
        var locale: String = "",
        var timezone: String = "",
        var client_ip: String = "",
        var create_time: Long = 0,
        var is_invalid: String = "",
        var system_id: String = "",
        var StatusCode: String = "",
        var organization_id: String = "",
        var display_name: String = "",
        var first_name: String = "",
        var last_name: String = "",
        var email: String = "",
        var cell_phone: String = "",
        var work_phone: String = "",
        var country_name: String = "",
        var region_id: String = "",
        var address: String = "",
        var relationship: String = "",
        var company_name: String = "",
        var country_id: String = "",
        var roles: ArrayList<Roles>? = ArrayList(),
        var last_time_stamp: String = ""
) : Serializable

data class Roles(
        var name: String = "",
        var system_id: String = "",
        var role_id: String = "",
        var role_name: String = "",
        var role_type: String = "",
        var account_id: String = "",
        var org_id: String = "",
        var company_id: String = "",
        var is_adm: String = ""
) : Serializable

/*
    Price Evaluate
 */
data class PriceEvaluate(
        var price_evaluate: PriceEvaluateData
)

data class PriceEvaluateData(
        var machine: ArrayList<Machine> = ArrayList(),
        var total_info: TotalInfo?,
        var car_full_info: CarFullInfo?
)

data class Machine(
        var name: String? = "",
        var car_empty: String? = "",
        var car_full: String? = ""
)

data class TotalInfo(
        var price: Int = 0,
        var car_empty: Int = 0,
        var car_full: Int = 0
)

data class CarFullInfo(
        var hour: Int = 0,
        var whtt: Int = 0,
        var price: Int = 0
)

/*
    Status
 */
data class Status(
        var facilities: ArrayList<Facilities>? = ArrayList()
)

data class Facilities(
        var title: String = "",
        var machines: ArrayList<Machines>? = ArrayList()
)

data class Machines(
        var name: String
)

/*
    Notifications
 */
data class Notifications(
        var notifications: ArrayList<Content>? = ArrayList()
)

data class Content(
        var title: String = "",
        var message: String = "",
        var time: String = "",
        var read: Int = 0,
        var expend: Int = 0
)


/*
    Facilities info
 */
data class FacilitiesInfo(
        var facilities: FacilitiesContent
) : Serializable

data class FacilitiesContent(
        var air_compressor: ArrayList<AirCompressor>,
        var accessory: Accessory
) : Serializable

data class AirCompressor(
        var title: String,
        var description: String,
        var effective_time: String,
        var warranty_time_start: String,
        var warranty_time_end: String,
        var model: String,
        var area: String,
        var name: String,
        var engineer: String,
        var show: Boolean = false//用來表示是否顯示擴張畫面的tag
) : Serializable

data class Accessory(
        var title: String
) : Serializable