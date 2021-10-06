package com.qoiu.holybibleapp.data.books

import com.qoiu.holybibleapp.core.Matcher
import com.qoiu.holybibleapp.core.Save


interface TestamentTemp : Matcher<String>, Save<String> {
    fun isEmpty(): Boolean

    class Base() : TestamentTemp {
        private var temp: String = ""
        override fun save(data: String) {
            temp = data
        }

        override fun isEmpty(): Boolean = temp.isEmpty()

        override fun matches(arg: String): Boolean = temp == arg
    }
}