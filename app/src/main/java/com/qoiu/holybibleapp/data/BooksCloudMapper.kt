package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.core.Abstract

interface BooksCloudMapper : Abstract.Mapper {

    fun map(listTo: List<Abstract.Object<BookData, ToBookMapper>>): List<BookData>

    class Base(private val toBookMapper: ToBookMapper) : BooksCloudMapper {
        override fun map(listTo: List<Abstract.Object<BookData, ToBookMapper>>): List<BookData> = listTo.map { bookCloud ->
            bookCloud.map(toBookMapper)
        }
    }
}
