package com.qoiu.holybibleapp.presentation.book

import android.content.Context
import com.qoiu.holybibleapp.core.Read
import com.qoiu.holybibleapp.core.Save

interface BookCache: Save<Pair<Int,String>>, Read<Pair<Int,String>> {

    class Base(context: Context): BookCache{
        private val sharedPreferences = context.getSharedPreferences(BOOK_ID_FILENAME,Context.MODE_PRIVATE)
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