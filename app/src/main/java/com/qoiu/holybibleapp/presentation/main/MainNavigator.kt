package com.qoiu.holybibleapp.presentation.main

import androidx.fragment.app.Fragment
import com.qoiu.holybibleapp.core.Read

interface MainNavigator: Read<Int> {
    fun getFragment(id: Int): Fragment
}