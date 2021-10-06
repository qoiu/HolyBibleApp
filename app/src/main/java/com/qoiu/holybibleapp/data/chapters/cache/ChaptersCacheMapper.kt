package com.qoiu.holybibleapp.data.chapters.cache

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.chapters.ChapterData
import com.qoiu.holybibleapp.data.chapters.ToChapterMapper

interface ChaptersCacheMapper : Abstract.Mapper.Data<List<ChapterDb>, List<ChapterData>> {

    class Base(private val toChapterMapper: ToChapterMapper): ChaptersCacheMapper{

        override fun map(data: List<ChapterDb>): List<ChapterData> =
            data.map { chapter-> chapter.map(toChapterMapper) }
    }
}