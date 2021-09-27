package com.qoiu.holybibleapp.core

interface Matcher<T> {
    fun matches(arg: T): Boolean
}