package com.qoiu.holybibleapp.presentation.main

import com.qoiu.holybibleapp.core.Communication

interface NavigationCommunication : Communication<Int> {
    class Base: Communication.Base<Int>(), NavigationCommunication

}
