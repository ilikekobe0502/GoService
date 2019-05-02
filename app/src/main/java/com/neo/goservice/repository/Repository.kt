package com.neo.goservice.repository

import android.app.Application
import com.neo.goservice.repository.provider.preferences.SharedPreferencesProvider
import com.neo.goservice.repository.remote.RemoteAPI
import com.neo.goservice.repository.remote.SsoApiClient
import com.neo.goservice.utils.MiscUtils
import io.reactivex.Single
import okhttp3.ResponseBody

class Repository(
    private var application: Application,
    private val sharedPreferencesProvider: SharedPreferencesProvider
) {

    val TAG = Repository::class.simpleName

    init {
        RemoteAPI.init(application)
    }

    /*
        Remote API
     */

    fun login(email: String, password: String): Single<ResponseBody> {
        return SsoApiClient.getInstance()!!.login(email, password,MiscUtils.getIpAddress(application))
    }
}