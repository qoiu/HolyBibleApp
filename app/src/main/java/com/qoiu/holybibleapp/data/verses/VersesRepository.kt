package com.qoiu.holybibleapp.data.verses

import com.qoiu.holybibleapp.core.Read
import com.qoiu.holybibleapp.data.verses.cache.VersesCacheDataSource
import com.qoiu.holybibleapp.data.verses.cache.VersesCacheMapper
import com.qoiu.holybibleapp.data.verses.cloud.VersesCloudDataSource
import com.qoiu.holybibleapp.data.verses.cloud.VersesCloudMapper

interface VersesRepository {

    suspend fun fetchVerses(): VersesData
    class Base(
        private val cloudDataSource: VersesCloudDataSource,
        private val cacheDataSource: VersesCacheDataSource,
        private val cloudMapper: VersesCloudMapper,
        private val cacheMapper: VersesCacheMapper,
        private val chapterCache: Read<Pair<Int,String>>,
        private val bookIdContainer: Read<Int>
    ) : VersesRepository{
        override suspend fun fetchVerses(): VersesData = try {
            val bookId = bookIdContainer.read()
            val chapterId = chapterCache.read().first
            val verseCacheList = cacheDataSource.fetchVerses(bookId, chapterId)
            if (verseCacheList.isEmpty()) {
                val verseCloudList = cloudDataSource.fetchVerses(bookId, chapterId)
                val verses = cloudMapper.map(verseCloudList)
                cacheDataSource.save(verses)
                VersesData.Success(verses)
            } else {
                VersesData.Success(cacheMapper.map(verseCacheList))
            }
        }catch (e:Exception){
            VersesData.Fail(e)
        }
    }
}