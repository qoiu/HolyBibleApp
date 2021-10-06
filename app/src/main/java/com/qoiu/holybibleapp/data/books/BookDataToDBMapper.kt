package com.qoiu.holybibleapp.data.books

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.books.cache.BookDB
import com.qoiu.holybibleapp.core.DbWrapper

interface BookDataToDBMapper : Abstract.Mapper {
    fun mapToDb(id: Int, name: String, testament: String, db: DbWrapper<BookDB>): BookDB

    class Base() : BookDataToDBMapper {
        override fun mapToDb(id: Int, name: String, testament: String, db: DbWrapper<BookDB>): BookDB {
            val bookDB = db.createObject(id)
            bookDB.name = name
            bookDB.testament = testament
            return bookDB
        }
    }
}
