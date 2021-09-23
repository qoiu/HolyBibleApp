package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.core.Abstract

interface ToBookMapper: Abstract.Mapper {

    fun map(id: Int, name: String) : BookData

    class Base() : ToBookMapper {
        override fun map(id: Int, name: String) = BookData(id,name)
    }
}