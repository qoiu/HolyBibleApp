package com.qoiu.holybibleapp.presentation

import org.junit.Test

class SavingCollapsedTest: BaseTest() {

    @Test
    fun test() = BooksPage().run {
        oldFirstElement.checkVisible()
        tap(oldTestamentPosition)
        oldFirstElement.checkDoesNotExist()

        killAppAndReturn()

        oldFirstElement.checkDoesNotExist()
    }
}