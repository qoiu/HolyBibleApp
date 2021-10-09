package com.qoiu.holybibleapp.presentation.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainViewModel(
    private val navigator: MainNavigator,
    private val communication: NavigationCommunication
) : ViewModel() {

    fun init(){
        communication.map(navigator.read())
    }

    fun getFragment(id: Int) =  navigator.getFragment(id)

    fun observe(owner: LifecycleOwner, observer: Observer<Int>) =
        communication.observe(owner, observer)

    fun navigateBack(): Boolean{
        val currentScreen = navigator.read()
        val exit = currentScreen == 0
        if(!exit){
            val newScreen = currentScreen - 1
            communication.map(newScreen)
        }
        return exit
    }
}