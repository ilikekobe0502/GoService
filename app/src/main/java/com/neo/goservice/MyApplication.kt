package com.neo.goservice

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }
}