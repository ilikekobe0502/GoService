package com.neo.goservice.repository.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


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