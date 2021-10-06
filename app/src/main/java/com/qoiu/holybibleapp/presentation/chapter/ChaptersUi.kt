package com.qoiu.holybibleapp.presentation.chapter

import com.qoiu.holybibleapp.core.Abstract

sealed class ChaptersUi : Abstract.Object<Unit,ChaptersCommunication>{
    abstract fun cache(uiDataCache: ChaptersUiDataCache): ChaptersUi

    data class Base(private val books: List<ChapterUi>) : ChaptersUi() {
        override fun map(mapper: ChaptersCommunication) = mapper.map(books)
        override fun cache(uiDataCache: ChaptersUiDataCache) = uiDataCache.cache(books)
    }

}
