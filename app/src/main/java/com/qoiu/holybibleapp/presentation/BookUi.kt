package com.qoiu.holybibleapp.presentation

import com.qoiu.holybibleapp.core.Abstract

sealed class BookUi: Abstract.Object<Unit,BookUi.StringMapper> {

    override fun map(mapper: StringMapper) = Unit

    object Progress : BookUi()

    class Base(
        private val id: Int, // TODO: 12.09.2021 use for getting chapters
        private val name: String
    ): BookUi(){
        override fun map(mapper: StringMapper) = mapper.map(name)
    }

    class Fail(private val message: String):BookUi(){
        override fun map(mapper: StringMapper) = mapper.map(message)
    }

    interface StringMapper: Abstract.Mapper{
        fun map(text: String)

        class Base(private val name: String)
    }
}