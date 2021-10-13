package com.qoiu.holybibleapp.data.chapters

import com.qoiu.holybibleapp.R
import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.ResourceProvider
import com.qoiu.holybibleapp.presentation.chapter.ChapterUi

interface ChapterIdToUiMapper: Abstract.Mapper {
    fun map(generatedId: Int, realId: Int): ChapterUi

    class Base(private val resourceProvider: ResourceProvider): ChapterIdToUiMapper{
        override fun map(generatedId: Int, realId: Int): ChapterUi =
            ChapterUi.Base(realId,resourceProvider.getString(R.string.chapter_number, realId))
    }

}
