package com.qoiu.holybibleapp.presentation.book

import com.qoiu.holybibleapp.core.*


sealed class BookUi : ComparableTextMapper<BookUi>, Matcher<Int>, Collapsing,
    Comparing<BookUi>{
    override fun map(mapper: TextMapper) = Unit
    override fun matches(arg: Int): Boolean = false

    open fun changeState(): BookUi = Progress
    open fun saveId(cache: CollapsedIdCache) = Unit
    open fun open(bookListener: BookAdapter.BookListener) = Unit

    object Empty : BookUi()
    object Progress : BookUi()

    abstract class Info(
        protected open val id: Int,
        protected open val name: String
    ) : BookUi() {
        override fun map(mapper: TextMapper) = mapper.map(name)
        override fun matches(arg: Int) = arg == id
    }

    data class Base(override val id: Int, override val name: String) : Info(id, name) {
        override fun sameContent(item: BookUi) = if (item is Base) {
            name == item.name
        } else false

        override fun same(item: BookUi) = item is Base && id == item.id

        override fun open(bookListener: BookAdapter.BookListener) = bookListener.showBook(id, name)
    }

    data class Testament(
        override val id: Int,
        override val name: String,
        private val collapsed: Boolean = false
    ) : Info(id, name) {
        override fun collapseOrExpand(listener: BookAdapter.CollapseListener) = listener.collapseOrExpand(id)

        override fun showCollapsed(mapper: CollapseMapper) = mapper.map(collapsed)

        override fun changeState(): BookUi = Testament(id, name, !collapsed)

        override fun isCollapsed(): Boolean = collapsed


        override fun same(item: BookUi) = item is Testament && id == item.id

        override fun sameContent(item: BookUi) = if (item is Testament) {
            name == item.name && collapsed == item.isCollapsed()
        } else false

        override fun saveId(cache: CollapsedIdCache) = cache.save(id)
    }

    data class Fail(private val message: String) : BookUi() {
        override fun map(mapper: TextMapper) = mapper.map(message)

        override fun sameContent(item: BookUi): Boolean = if(item is Fail){
            message == item.message
        }else false

        override fun same(item: BookUi): Boolean = sameContent(item)
    }
}

interface Collapsing {
    fun collapseOrExpand(listener: BookAdapter.CollapseListener) = Unit
    fun showCollapsed(mapper: CollapseMapper) = Unit
    fun isCollapsed() = false
}
