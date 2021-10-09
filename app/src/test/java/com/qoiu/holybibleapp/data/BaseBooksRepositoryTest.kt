package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.data.books.BookData
import com.qoiu.holybibleapp.data.books.ToBookMapper

abstract class BaseBooksRepositoryTest{

    protected class TestToBookMapper: ToBookMapper {
        override fun map(id: Int, name: String, testament: String) = BookData(id, name,testament)
    }
}