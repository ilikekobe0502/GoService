package com.neo.goservice.repository.data

import java.io.Serializable


/*
    Login Page
 */
data class Login(
        var StatusCode: String? = "",
        var account_id: String? = "",
        var compay_id: String? = "",
        var last_time_stamp: String? = "",
        var Token: String? = "",
        var Role: ArrayList<Role>? = null
)

data class Role(
        var id: String? = ""
)

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