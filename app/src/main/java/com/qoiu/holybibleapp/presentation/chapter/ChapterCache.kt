package com.qoiu.holybibleapp.presentation.chapter

import com.qoiu.holybibleapp.core.PreferenceProvider
import com.qoiu.holybibleapp.core.Read
import com.qoiu.holybibleapp.core.Save

interface ChapterCache : Save<Int>, Read<Int> {

    class Base(preferenceProvider: PreferenceProvider) : ChapterCache {

        private val sharedPreferences = preferenceProvider.provideSharedPreference(
            CHAPTER_ID_FILENAME)
        override fun save(data: Int) {
            sharedPreferences.edit().putInt(CHAPTER_ID_KEY,data).apply()
        }

        override fun read(): Int = sharedPreferences.getInt(CHAPTER_ID_KEY,0)
    }

    private companion object {
        const val CHAPTER_ID_FILENAME = "chapterId"
        const val CHAPTER_ID_KEY = "chapterIdKey"
    }
}