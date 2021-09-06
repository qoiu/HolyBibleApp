package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.Book

interface BookCloudMapper: Abstract.Mapper {

    fun map(id: Int, name: String) : Book

    class Base() : BookCloudMapper{
        override fun map(id: Int, name: String) = Book(id,name)
    }
}