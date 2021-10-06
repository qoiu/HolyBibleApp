package com.qoiu.holybibleapp.presentation

import com.qoiu.holybibleapp.presentation.book.BookUi
import com.qoiu.holybibleapp.presentation.book.BooksUi

interface UiDataCache {
    fun cache(list: List<BookUi>): BooksUi
    fun changeState(id: Int): List<BookUi>
    fun saveState()


    class Base(private val cache: CollapsedIdCache) : UiDataCache {
        private val cachedList = ArrayList<BookUi>()

        override fun cache(list: List<BookUi>): BooksUi {
            cachedList.clear()
            cachedList.addAll(list)
            val ids = cache.read()
            var newList: List<BookUi> = ArrayList(list)
            ids.forEach { id ->
                newList = changeState(id)
            }
            return BooksUi.Base(newList)
        }

        override fun changeState(id: Int): List<BookUi> {
            val newList = ArrayList<BookUi>()
            val item = cachedList.find {
                it.matches(id)
            }
            var add = false
            cachedList.forEachIndexed { index, book ->
                if (book is BookUi.Testament) {
                    if (book == item) {
                        val element = book.changeState()
                        cachedList[index] = element
                        newList.add(element)
                        add = !element.isCollapsed()
                    } else {
                        newList.add(book)
                        add = !book.isCollapsed()
                    }
                } else {
                    if (add) newList.add(book)
                }
            }
            return newList
        }

        override fun saveState() {
            cache.start()
            cachedList.filter {
                it.isCollapsed()
            }.forEach {
                it.saveId(cache)
            }
            cache.finish()
        }
    }
}
