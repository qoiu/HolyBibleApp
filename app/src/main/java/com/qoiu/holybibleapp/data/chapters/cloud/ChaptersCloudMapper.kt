package com.qoiu.holybibleapp.data.chapters.cloud

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.chapters.ChapterData
import com.qoiu.holybibleapp.data.chapters.ToChapterMapper

interface ChaptersCloudMapper : Abstract.Mapper{

    fun map(list: List<ChapterCloud>, bookId: Int): List<ChapterData>

    class Base(private val toChapterMapper: ToChapterMapper): ChaptersCloudMapper{
        override fun map(list: List<ChapterCloud>, bookId: Int): List<ChapterData> =
            list.map { chapter->
                chapter.map(toChapterMapper)
            }
    }
}