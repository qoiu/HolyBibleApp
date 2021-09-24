package com.qoiu.holybibleapp.data.cache

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.BookData
import com.qoiu.holybibleapp.data.ToBookMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookDB : RealmObject(), Abstract.Object<BookData, ToBookMapper>{
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var testament: String = ""
    override fun map(mapper: ToBookMapper) = BookData(id,name, testament)
}
