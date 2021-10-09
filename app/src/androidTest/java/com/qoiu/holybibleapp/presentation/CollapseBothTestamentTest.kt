package com.qoiu.holybibleapp.presentation

import org.junit.Test

open class CollapseBothTestamentTest: BaseTest() {

    @Test
    fun test_collapse_first_then_second() = BooksPage().run {
        oldFirstElement.checkVisible()
        tap(oldTestamentPosition)
        oldFirstElement.checkDoesNotExist()

        newFirstElement.checkVisible()
        tap(newTestamentPositionIfFirstCollapsed)
        newFirstElement.checkDoesNotExist()
    }

    @Test
    fun test_collapse_second_then_first() = BooksPage().run {
        newFirstElement.checkVisible()
        tap(newTestamentPositionIfFirstExpanded)
        newFirstElement.checkDoesNotExist()

        oldFirstElement.checkVisible()
        tap(oldTestamentPosition)
        oldFirstElement.checkDoesNotExist()

    }
}