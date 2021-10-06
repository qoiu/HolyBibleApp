package com.qoiu.holybibleapp.presentation.chapter

interface ChaptersUiDataCache {

    fun cache(list: List<ChapterUi>): ChaptersUi
    fun changeState(id: Int): List<ChapterUi>
    fun saveState()

}
