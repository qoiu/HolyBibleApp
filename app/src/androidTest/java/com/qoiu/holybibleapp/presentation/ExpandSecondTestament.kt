package com.qoiu.holybibleapp.presentation

import org.junit.Test

class ExpandSecondTestament: BaseTest() {

    @Test
    fun test() = BooksPage().run {
        newFirstElement.checkVisible()
        tap(newTestamentPositionIfFirstExpanded)
        newFirstElement.checkDoesNotExist()
        tap(newTestamentPositionIfFirstExpanded)
        newFirstElement.checkVisible()
    }
}