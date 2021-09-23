package com.qoiu.holybibleapp.data.cache

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.BookData
import com.qoiu.holybibleapp.data.ToBookMapper

interface BooksCacheMapper: Abstract.Mapper{

    fun map(listTo: List<Abstract.Object<BookData, ToBookMapper>>): List<BookData>

    class Base(private val toBookMapper: ToBookMapper) : BooksCacheMapper {
        override fun map(listTo: List<Abstract.Object<BookData, ToBookMapper>>): List<BookData> = listTo.map { bookCache ->
            bookCache.map(toBookMapper)
        }
    }
}