package com.qoiu.holybibleapp.presentation.book

import com.qoiu.holybibleapp.core.PreferenceProvider
import com.qoiu.holybibleapp.core.Read
import com.qoiu.holybibleapp.core.Save

interface BookCache: Save<Pair<Int,String>>, Read<Pair<Int,String>> {

    class Base(preferenceProvider: PreferenceProvider): BookCache{
        private val sharedPreferences = preferenceProvider.provideSharedPreference(BOOK_ID_FILENAME)
        override fun save(data: Pair<Int, String>) = sharedPreferences.edit()
            .putInt(BOOK_ID_KEY,data.first)
            .putString(BOOK_NAME_KEY, data.second)
            .apply()

        override fun read(): Pair<Int, String> = Pair(
            sharedPreferences.getInt(BOOK_ID_KEY,0),
            sharedPreferences.getString(BOOK_NAME_KEY,"")?:""
        )

        private companion object {
            const val BOOK_ID_FILENAME = "bookId"
            const val BOOK_ID_KEY = "bookIdKey"
            const val BOOK_NAME_KEY = "bookNameKey"
        }
    }
}