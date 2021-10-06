package com.qoiu.holybibleapp.presentation

import android.content.Context
import com.qoiu.holybibleapp.core.Read
import com.qoiu.holybibleapp.core.Save


interface CollapsedIdCache : Save<Int>, Read<Set<Int>> {

    fun start()
    fun finish()

    class Base(context: Context) : CollapsedIdCache {
        private val sharedPreferences = context.getSharedPreferences(
            ID_LIST_NAME, Context.MODE_PRIVATE
        )
        private val idSet = mutableListOf<Int>()

        override fun read(): Set<Int> {
            val set = sharedPreferences.getStringSet(IDS_KEY, setOf())?: emptySet()
            return set.map { it.toInt() }.toSet()
        }

        override fun save(data: Int) {
            idSet.add(data)
        }

        override fun start() {
            idSet.clear()
        }

        override fun finish() {
            val set = idSet.map { it.toString() }.toSet()
            sharedPreferences.edit().putStringSet(IDS_KEY, set).apply()
        }

        private companion object {
            const val ID_LIST_NAME = "idList"
            const val IDS_KEY = "collapsedItemsIdKeys"
        }
    }

    class Empty : CollapsedIdCache{
        override fun save(data: Int) = Unit
        override fun read(): Set<Int> = emptySet()
        override fun start() = Unit
        override fun finish() = Unit
    }
}