package com.qoiu.holybibleapp.presentation.chapter

import com.qoiu.holybibleapp.data.chapters.ChapterId
import com.qoiu.holybibleapp.data.chapters.ChapterIdToUiMapper
import com.qoiu.holybibleapp.domain.chapter.ChapterDomainToUiMapper

class BaseChapterDomainToUiMapper(private val mapper: ChapterIdToUiMapper) : ChapterDomainToUiMapper {
    override fun map(data: ChapterId): ChapterUi = data.map(mapper)
}