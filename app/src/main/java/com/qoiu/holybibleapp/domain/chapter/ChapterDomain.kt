package com.qoiu.holybibleapp.domain.chapter

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.chapters.ChapterId
import com.qoiu.holybibleapp.presentation.chapter.ChapterUi

data class ChapterDomain(private val chapterId: ChapterId) : Abstract.Object<ChapterUi,ChapterDomainToUiMapper>   {
    override fun map(mapper: ChapterDomainToUiMapper): ChapterUi = mapper.map(chapterId)
}