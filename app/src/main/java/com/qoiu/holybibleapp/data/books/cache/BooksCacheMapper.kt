package com.qoiu.holybibleapp.data.books.cache

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.books.BookData
import com.qoiu.holybibleapp.data.books.CommonBookData
import com.qoiu.holybibleapp.data.books.ToBookMapper

interface BooksCacheMapper : Abstract.Mapper.Data<List<CommonBookData>, List<BookData>> {

    class Base(private val toBookMapper: ToBookMapper) : BooksCacheMapper {
        override fun map(data: List<CommonBookData>): List<BookData> =
            data.map { bookCache ->
                bookCache.map(toBookMapper)
            }
    }
}