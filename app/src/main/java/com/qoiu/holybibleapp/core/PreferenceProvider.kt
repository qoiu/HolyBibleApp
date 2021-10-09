package com.qoiu.holybibleapp.core

import android.content.SharedPreferences

interface PreferenceProvider {
    fun provideSharedPreference(name: String): SharedPreferences
}