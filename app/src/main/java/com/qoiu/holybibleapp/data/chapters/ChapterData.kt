package com.qoiu.holybibleapp.data.chapters

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.DbWrapper
import com.qoiu.holybibleapp.data.chapters.cache.ChapterDb
import com.qoiu.holybibleapp.domain.chapter.ChapterDomain

data class ChapterData(private val chapterId: ChapterId) :
    Abstract.Object.ToDb<ChapterDb, ChapterDataToDbMapper>,
    Abstract.Object<ChapterDomain, ChapterDataToDomainMapper> {
    override fun mapBy(mapper: ChapterDataToDbMapper, db: DbWrapper<ChapterDb>): ChapterDb =
        mapper.mapToDb(chapterId, db)

    override fun map(mapper: ChapterDataToDomainMapper): ChapterDomain = mapper.map(chapterId)
}

