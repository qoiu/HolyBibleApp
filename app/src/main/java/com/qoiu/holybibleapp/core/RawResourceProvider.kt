package com.qoiu.holybibleapp.core

import androidx.annotation.RawRes

interface RawResourceProvider {
    fun readText(@RawRes id: Int): String
}