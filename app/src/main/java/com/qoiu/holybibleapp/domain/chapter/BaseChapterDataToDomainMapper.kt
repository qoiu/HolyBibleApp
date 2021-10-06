package com.qoiu.holybibleapp.domain.chapter

import com.qoiu.holybibleapp.data.chapters.ChapterDataToDomainMapper
import com.qoiu.holybibleapp.data.chapters.ChapterId

class BaseChapterDataToDomainMapper: ChapterDataToDomainMapper {
    override fun map(data: ChapterId): ChapterDomain = ChapterDomain(data)
}