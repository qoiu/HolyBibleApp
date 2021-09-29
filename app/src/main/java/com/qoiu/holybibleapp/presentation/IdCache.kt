package com.qoiu.holybibleapp.presentation

import android.content.Context


interface IdCache {
    fun read(): Set<Int>
    fun save(id: Int)
    fun start()
    fun finish()

    class Base(context: Context) : IdCache {
        private val sharedPreferences = context.getSharedPreferences(ID_LIST_NAME,
            Context.MODE_PRIVATE
        )
        private val idList = mutableListOf<Int>()

        override fun read(): Set<Int> {
            val set = sharedPreferences.getStringSet(IDS_KEY, setOf())?: emptySet()
            return set.map { it.toInt() }.toSet()
        }

        override fun save(id: Int) {
            idList.add(id)
        }

        override fun start() {
            idList.clear()
        }

        override fun finish() {
            val set = idList.map { it.toString() }.toSet()
            sharedPreferences.edit().putStringSet(IDS_KEY, set).apply()
        }

        private companion object {
            const val ID_LIST_NAME = "idList"
            const val IDS_KEY = "collapsedItemsIdKeys"
        }
    }
}