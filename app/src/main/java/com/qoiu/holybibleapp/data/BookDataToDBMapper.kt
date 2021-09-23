package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.cache.BookDB
import io.realm.Realm

interface BookDataToDBMapper : Abstract.Mapper {
    fun mapToDb(id: Int, name: String, realm: Realm) : BookDB
    class Base() : BookDataToDBMapper {
        override fun mapToDb(id: Int, name: String, realm: Realm): BookDB {
            val bookDB = realm.createObject(BookDB::class.java, id)
            bookDB.name = name
            return bookDB
        }
    }
}
