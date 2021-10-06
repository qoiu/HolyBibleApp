package com.qoiu.holybibleapp.data.chapters

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.domain.chapter.ChaptersDataToDomainMapper
import com.qoiu.holybibleapp.domain.chapter.ChaptersDomain
import java.lang.Exception

sealed class ChaptersData : Abstract.Object<ChaptersDomain, ChaptersDataToDomainMapper> {

    data class Success(private val chapters: List<ChapterData>) : ChaptersData() {
        override fun map(mapper: ChaptersDataToDomainMapper): ChaptersDomain = mapper.map(chapters)
    }

    data class Fail(private val e: Exception) : ChaptersData() {
        override fun map(mapper: ChaptersDataToDomainMapper): ChaptersDomain = mapper.map(e)
    }
}