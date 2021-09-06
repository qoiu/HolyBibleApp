package com.qoiu.holybibleapp.data.cache

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.Book

interface BookCacheMapper: Abstract.Mapper {
    fun map(bookdb: BookDB): Book

    class Base: BookCacheMapper{
        override fun map(bookdb: BookDB) = Book(bookdb.id,bookdb.name)
    }
}