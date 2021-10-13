package com.qoiu.holybibleapp.domain.chapter

import com.qoiu.holybibleapp.data.chapters.ChapterData
import com.qoiu.holybibleapp.data.chapters.ChapterDataToDomainMapper

class BaseChaptersDataToDomainMapper(private val mapper: ChapterDataToDomainMapper) :
    ChaptersDataToDomainMapper() {
    override fun map(data: List<ChapterData>): ChaptersDomain =
        ChaptersDomain.Success(data.map { chapterData ->
            chapterData.map(mapper)
        })

    override fun map(e: Exception): ChaptersDomain = ChaptersDomain.Fail(errorType(e))
}