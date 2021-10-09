package com.qoiu.holybibleapp.presentation

import org.junit.Test

class CollapsingSecondTestamentTest: BaseTest() {

    @Test
    fun test() = booksPage.run {
        newFirstElement.checkVisible()
        newTestament.tap()
        newFirstElement.checkDoesNotExist()
    }
}