package com.qoiu.holybibleapp.data.books.cloud

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.books.BookData
import com.qoiu.holybibleapp.data.books.ToBookMapper

interface BooksCloudMapper : Abstract.Mapper.Data<List<BookCloud>, List<BookData>> {

    class Base(private val toBookMapper: ToBookMapper) : BooksCloudMapper {
        override fun map(data: List<BookCloud>): List<BookData> = data.map { bookCloud ->
            bookCloud.map(toBookMapper)
        }
    }
}
