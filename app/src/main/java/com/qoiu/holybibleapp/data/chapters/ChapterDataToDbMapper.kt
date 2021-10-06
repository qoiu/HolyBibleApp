package com.qoiu.holybibleapp.data.chapters

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.DbWrapper
import com.qoiu.holybibleapp.data.chapters.cache.ChapterDb

interface ChapterDataToDbMapper : Abstract.Mapper{
    fun mapToDb(chapterId: ChapterId, db: DbWrapper<ChapterDb>): ChapterDb

    class Base() : ChapterDataToDbMapper {
        override fun mapToDb(chapterId: ChapterId, db: DbWrapper<ChapterDb>) = chapterId.mapToDb(db)
    }
}