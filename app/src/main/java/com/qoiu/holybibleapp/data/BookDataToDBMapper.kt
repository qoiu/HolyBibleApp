package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.cache.BookDB
import com.qoiu.holybibleapp.data.cache.DbWrapper
import io.realm.Realm

interface BookDataToDBMapper : Abstract.Mapper {
    fun mapToDb(id: Int, name: String, testament: String,db: DbWrapper): BookDB
    class Base() : BookDataToDBMapper {
        override fun mapToDb(id: Int, name: String, testament: String, db: DbWrapper): BookDB {
            val bookDB = db.createObject(id)
            bookDB.name = name
            bookDB.testament = testament
            return bookDB
        }
    }
}
