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