package com.qoiu.holybibleapp.presentation

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.Matcher

interface Comparing {
    fun same(bookUi: BookUi): Boolean = false
    fun sameContent(bookUi: BookUi): Boolean = false
}

interface Collapsing {
    fun collapseOrExpand(listener: BibleAdapter.BibleViewHolder.CollapseListener) = Unit
    fun showCollapsed(mapper: BookUi.CollapseMapper) = Unit
    fun isCollapsed() = false
}

sealed class BookUi : Abstract.Object<Unit, BookUi.StringMapper>, Matcher<Int>, Collapsing,
    Comparing {
    override fun map(mapper: StringMapper) = Unit
    override fun matches(arg: Int): Boolean = false

    open fun changeState(): BookUi = Progress
    open fun saveId(cache: IdCache) = Unit

    object Progress : BookUi()

    abstract class Info(
        protected open val id: Int,
        protected open val name: String
    ) : BookUi() {
        override fun map(mapper: StringMapper) = mapper.map(name)
        override fun matches(arg: Int) = arg == id
    }

    data class Base(override val id: Int, override val name: String) : Info(id, name) {
        override fun sameContent(bookUi: BookUi) = if (bookUi is Base) {
            name == bookUi.name
        } else false

        override fun same(bookUi: BookUi) = bookUi is Base && id == bookUi.id
    }

    data class Testament(
        override val id: Int,
        override val name: String,
        private val collapsed: Boolean = false
    ) : Info(id, name) {
        override fun collapseOrExpand(listener: BibleAdapter.BibleViewHolder.CollapseListener) {
            listener.collapseOrExpand(id)
        }

        override fun changeState(): BookUi = Testament(id, name, !collapsed)

        override fun isCollapsed(): Boolean = collapsed

        override fun showCollapsed(mapper: CollapseMapper) = mapper.show(collapsed)

        override fun same(bookUi: BookUi) = bookUi is Testament && id == bookUi.id

        override fun sameContent(bookUi: BookUi) = if (bookUi is Testament) {
            name == bookUi.name && collapsed == bookUi.isCollapsed()
        } else false

        override fun saveId(cache: IdCache) = cache.save(id)
    }

    data class Fail(private val message: String) : BookUi() {
        override fun map(mapper: StringMapper) = mapper.map(message)
    }

    interface StringMapper : Abstract.Mapper {
        fun map(text: String)

        class Base(private val name: String)
    }

    interface CollapseMapper : Abstract.Mapper {
        fun show(collapsed: Boolean)
    }
}
