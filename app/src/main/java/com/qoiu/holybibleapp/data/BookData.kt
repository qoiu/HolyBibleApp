package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.cache.BookDB
import com.qoiu.holybibleapp.data.cache.DbWrapper
import com.qoiu.holybibleapp.domain.BookDataToDomainMapper
import com.qoiu.holybibleapp.domain.BookDomain

class BookData(private val id: Int, private val name: String) :
    ToBookDb<BookDB, BookDataToDBMapper>,
    Abstract.Object<BookDomain, BookDataToDomainMapper> {
    override fun map(mapper: BookDataToDomainMapper): BookDomain = mapper.map(id, name)
    override fun mapTo(mapper: BookDataToDBMapper, db: DbWrapper): BookDB =
        mapper.mapToDb(id, name, db)
}

interface ToBookDb<T, M : Abstract.Mapper> {

    fun mapTo(mapper: M, db: DbWrapper): T

}