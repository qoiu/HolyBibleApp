package com.qoiu.holybibleapp.presentation

import org.junit.Test

class ExpandFirstTestament: BaseTest() {

    @Test
    fun test() = BooksPage().run {

        oldFirstElement.checkVisible()
        tap(oldTestamentPosition)
        oldFirstElement.checkDoesNotExist()
        tap(oldTestamentPosition)
        oldFirstElement.checkVisible()
    }
}