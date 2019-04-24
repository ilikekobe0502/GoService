package com.neo.goservice.repository.provider.preferences

import android.content.SharedPreferences

interface AppSharedPreferences {
    fun sharedPreferences(): SharedPreferences
}