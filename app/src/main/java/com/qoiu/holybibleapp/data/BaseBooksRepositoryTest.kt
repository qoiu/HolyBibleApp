package com.qoiu.holybibleapp.data

abstract class BaseBooksRepositoryTest{

    protected class TestToBookMapper: ToBookMapper{
        override fun map(id: Int, name: String): BookData = BookData(id, name)
    }
}