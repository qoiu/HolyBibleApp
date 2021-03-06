package com.qoiu.holybibleapp.data.chapters.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface ChaptersCloudDataSource {
    suspend fun fetchChapters(bookId: Int): List<ChapterCloud>

    class Base(
        private val service: ChapterService,
        private val gson: Gson
    ): ChaptersCloudDataSource{
        override suspend fun fetchChapters(bookId: Int): List<ChapterCloud> =
            gson.fromJson(service.fetchChapters(bookId).string(),
            object : TypeToken<List<ChapterCloud>>(){}.type)
    }
}