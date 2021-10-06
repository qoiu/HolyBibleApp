package com.qoiu.holybibleapp.presentation.chapter

import com.qoiu.holybibleapp.core.ErrorType
import com.qoiu.holybibleapp.core.ResourceProvider
import com.qoiu.holybibleapp.domain.chapter.ChapterDomain
import com.qoiu.holybibleapp.domain.chapter.ChapterDomainToUiMapper
import com.qoiu.holybibleapp.domain.chapter.ChaptersDomainToUiMapper

class BaseChaptersDomainToUiMapper(
    private val mapper: ChapterDomainToUiMapper,
    resourceProvider: ResourceProvider
) : ChaptersDomainToUiMapper(resourceProvider) {
    override fun map(data: List<ChapterDomain>): ChaptersUi = ChaptersUi.Base(data.map {
        it.map(mapper)
    })

    override fun map(errorType: ErrorType): ChaptersUi =
        ChaptersUi.Base(listOf(ChapterUi.Fail(errorMessage(errorType))))
}