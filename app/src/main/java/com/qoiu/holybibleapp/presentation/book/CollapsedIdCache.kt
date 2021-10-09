package com.qoiu.holybibleapp.presentation.book

import com.qoiu.holybibleapp.core.PreferenceProvider
import com.qoiu.holybibleapp.core.Read
import com.qoiu.holybibleapp.core.Save


interface CollapsedIdCache : Save<Int>, Read<Set<Int>> {

    fun start()
    fun finish()

    abstract class Abstract(preferenceProvider: PreferenceProvider): CollapsedIdCache {
        private val sharedPreferences by lazy {
            preferenceProvider.provideSharedPreference(getFileName())
        }
        private val idSet = mutableListOf<Int>()

        override fun read(): Set<Int> {
            val set = sharedPreferences.getStringSet(getKey(), setOf())?: emptySet()
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
            sharedPreferences.edit().putStringSet(getKey(), set).apply()
        }

        abstract fun getFileName():String
        abstract fun getKey():String

    }

    class Base(preferenceProvider: PreferenceProvider) : Abstract(preferenceProvider) {

        override fun getFileName(): String = ID_LIST_NAME

        override fun getKey(): String = IDS_KEY

        private companion object {
            const val ID_LIST_NAME = "idList"
            const val IDS_KEY = "collapsedItemsIdKeys"
        }
    }

    class Mock(preferenceProvider: PreferenceProvider) : Abstract(preferenceProvider){

        override fun getFileName() = ID_LIST_NAME

        override fun getKey() = IDS_KEY

        private companion object {
            const val ID_LIST_NAME = "MockIdList"
            const val IDS_KEY = "MockCollapsedItemsIdKeys"
        }
    }

    class Empty : CollapsedIdCache {
        override fun save(data: Int) = Unit
        override fun read(): Set<Int> = emptySet()
        override fun start() = Unit
        override fun finish() = Unit
    }
}