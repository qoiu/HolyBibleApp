package com.qoiu.holybibleapp.data.books

import com.qoiu.holybibleapp.core.*
import com.qoiu.holybibleapp.data.books.cache.BookDB
import com.qoiu.holybibleapp.domain.book.BookDataToDomainMapper
import com.qoiu.holybibleapp.domain.book.BookDomain

data class BookData(private val id: Int, private val name: String, private val testament: String) :
    Abstract.Object<BookDomain, BookDataToDomainMapper>,
    Abstract.Object.ToDb<BookDB, BookDataToDBMapper>,
        Matcher<TestamentTemp>,
        Save<TestamentTemp>
{
    override fun map(mapper: BookDataToDomainMapper): BookDomain = mapper.map(id, name)

    override fun mapBy(mapper: BookDataToDBMapper, db: DbWrapper<BookDB>): BookDB  =
        mapper.mapToDb(id,name,testament,db)

    override fun matches(arg: TestamentTemp): Boolean = arg.matches(testament)

    override fun save(data: TestamentTemp) = data.save(testament)
}