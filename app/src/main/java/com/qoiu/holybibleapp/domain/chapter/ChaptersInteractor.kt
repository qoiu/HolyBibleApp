package com.qoiu.holybibleapp.domain.chapter

import com.qoiu.holybibleapp.data.chapters.ChaptersRepository

interface ChaptersInteractor {

    suspend fun fetchChapters() : ChaptersDomain

    class Base(
        private val repository: ChaptersRepository,
        private val mapper: ChaptersDataToDomainMapper
    ) : ChaptersInteractor{
        override suspend fun fetchChapters(): ChaptersDomain = repository.fetchChapters().map(mapper)
    }
}