package com.qoiu.holybibleapp.data.cache

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.Book
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookDB : RealmObject(), Abstract.Mappable<Book, BookCacheMapper>{
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    override fun map(mapper: BookCacheMapper) = Book(id,name)
}
