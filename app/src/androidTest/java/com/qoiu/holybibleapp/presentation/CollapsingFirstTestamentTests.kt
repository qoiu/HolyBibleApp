package com.qoiu.holybibleapp.presentation

import org.junit.Test

class CollapsingFirstTestamentTests: BaseTest(){

    @Test
    fun test()=booksPage.run {
        oldFirstElement.checkVisible()
        oldTestament.tap()
        oldFirstElement.checkDoesNotExist()
    }

}