package com.qoiu.holybibleapp.data.books.cache

import com.qoiu.holybibleapp.data.books.CommonBookData
import com.qoiu.holybibleapp.data.books.ToBookMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookDB : RealmObject(), CommonBookData {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var testament: String = ""
    override fun map(mapper: ToBookMapper) = mapper.map(id, name, testament)
}
