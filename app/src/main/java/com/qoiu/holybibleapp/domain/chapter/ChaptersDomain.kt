package com.qoiu.holybibleapp.domain.chapter

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.ErrorType
import com.qoiu.holybibleapp.presentation.chapter.ChaptersUi

sealed class ChaptersDomain : Abstract.Object<ChaptersUi,ChaptersDomainToUiMapper> {
    data class Success(private val books: List<ChapterDomain>) : ChaptersDomain() {
        override fun map(mapper: ChaptersDomainToUiMapper): ChaptersUi = mapper.map(books)
    }

    data class Fail(private val e: ErrorType) : ChaptersDomain() {
        override fun map(mapper: ChaptersDomainToUiMapper) = mapper.map(e)
    }
}