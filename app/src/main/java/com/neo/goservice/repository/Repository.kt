package com.neo.goservice.repository

import android.app.Application
import com.google.gson.Gson
import com.neo.goservice.repository.data.Login
import com.neo.goservice.repository.provider.preferences.PreferencesKey.TOKEN
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

    val mGson: Gson = Gson()

    init {
        RemoteAPI.init(application)
    }

    /*
        Remote API
     */

    fun login(email: String, password: String, companyId: String = ""): Single<ResponseBody> {
        return SsoApiClient.getInstance()!!.login(email, password, MiscUtils.getIpAddress(), companyId)
    }

    fun ssoLogin(): Single<ResponseBody> {
        return getToken().flatMap { SsoApiClient.getInstance()!!.ssoLogin(it, MiscUtils.getIpAddress()) }
    }


    /*
        Local
     */

    /**
     * 暫存 User 資料
     */
    fun saveToken(token: String) {
        sharedPreferencesProvider.sharedPreferences().edit().putString(TOKEN, token).apply()
    }

    /**
     * 取得 User 資料
     */
    fun getToken(): Single<String> {
        return Single.fromCallable { sharedPreferencesProvider.sharedPreferences().getString(TOKEN, "") }
    }
}