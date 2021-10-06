package com.qoiu.holybibleapp.data.chapters

import com.qoiu.holybibleapp.core.Read
import com.qoiu.holybibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.qoiu.holybibleapp.data.chapters.cache.ChaptersCacheMapper
import com.qoiu.holybibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.qoiu.holybibleapp.data.chapters.cloud.ChaptersCloudMapper
import java.lang.Exception

interface ChaptersRepository {

    suspend fun fetchChapters(): ChaptersData

    class Base(
        private val cloudDataSource: ChaptersCloudDataSource,
        private val cacheDataSource: ChaptersCacheDataSource,
        private val chaptersCloudMapper: ChaptersCloudMapper,
        private val chaptersCacheMapper: ChaptersCacheMapper,
        private val bookIdContainer: Read<Pair<Int,String>>
    ) : ChaptersRepository {
        override suspend fun fetchChapters() = try {
            val bookId = bookIdContainer.read().first
            val chaptersCacheList = cacheDataSource.fetchChapters(bookId)
            if(chaptersCacheList.isEmpty()){
                val chaptersCloudList = cloudDataSource.fetchChapters(bookId)
                val chapters = chaptersCloudMapper.map(chaptersCloudList,bookId)
                cacheDataSource.save(chapters)
                ChaptersData.Success(chapters)
            } else{
                ChaptersData.Success(chaptersCacheMapper.map(chaptersCacheList))
            }
        } catch (e: Exception) {
            ChaptersData.Fail(e)
        }
    }
}