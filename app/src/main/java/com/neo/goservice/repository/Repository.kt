package com.neo.goservice.repository

import android.app.Application
import com.neo.goservice.repository.provider.preferences.SharedPreferencesProvider
import com.neo.goservice.repository.remote.RemoteAPI

class Repository(private var application: Application, private val sharedPreferencesProvider: SharedPreferencesProvider) {

    val TAG = Repository::class.simpleName

    init {
        RemoteAPI.init(application)
    }
}