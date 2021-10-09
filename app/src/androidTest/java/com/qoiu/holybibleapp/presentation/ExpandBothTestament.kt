package com.qoiu.holybibleapp.presentation

import org.junit.Test

class ExpandBothTestament : BaseTest() {

    @Test
    fun test_expand_first_then_second() = BooksPage().run {

        oldFirstElement.checkVisible()
        tap(oldTestamentPosition)
        oldFirstElement.checkDoesNotExist()

        newFirstElement.checkVisible()
        tap(newTestamentPositionIfFirstCollapsed)
        newFirstElement.checkDoesNotExist()

        oldFirstElement.checkDoesNotExist()
        tap(oldTestamentPosition)
        oldFirstElement.checkVisible()

        newFirstElement.checkDoesNotExist()
        tap(newTestamentPositionIfFirstExpanded)
        newFirstElement.checkVisible()
    }

    @Test
    fun test_expand_second_then_first() = BooksPage().run {

        newFirstElement.checkVisible()
        tap(newTestamentPositionIfFirstExpanded)
        newFirstElement.checkDoesNotExist()

        oldFirstElement.checkVisible()
        tap(oldTestamentPosition)
        oldFirstElement.checkDoesNotExist()

        newFirstElement.checkDoesNotExist()
        tap(newTestamentPositionIfFirstCollapsed)
        newFirstElement.checkVisible()

        oldFirstElement.checkDoesNotExist()
        tap(oldTestamentPosition)
        oldFirstElement.checkVisible()
    }
}