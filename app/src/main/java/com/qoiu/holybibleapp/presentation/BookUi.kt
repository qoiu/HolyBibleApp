package com.qoiu.holybibleapp.presentation

import com.qoiu.holybibleapp.core.Abstract

sealed class BookUi : Abstract.Object<Unit, BookUi.StringMapper> {

    override fun map(mapper: StringMapper) = Unit

    object Progress : BookUi()

    abstract class Info(
        private val id: Int,
        private val name: String
    ) : BookUi() {
        override fun map(mapper: StringMapper) = mapper.map(name)
    }

    open class Base(id: Int, name: String) : Info(id, name)

    class Testament(id: Int, name: String) : Info(id, name)

    class Fail(private val message: String) : BookUi() {
        override fun map(mapper: StringMapper) = mapper.map(message)
    }

    interface StringMapper : Abstract.Mapper {
        fun map(text: String)

        class Base(private val name: String)
    }
}