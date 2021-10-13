package com.qoiu.holybibleapp.presentation.chapter

import android.view.View
import com.qoiu.holybibleapp.core.ComparableTextMapper
import com.qoiu.holybibleapp.core.TextMapper

sealed class ChapterUi : ComparableTextMapper<ChapterUi>{

    open fun open(show: Show) = Unit

    class Base(
        private val id: Int,
        private val text: String
    ) : ChapterUi() {
        override fun map(mapper: TextMapper) = mapper.map(text)
        override fun open(show: Show) {
            show.show(id)
        }
    }

    class Fail(
        private val message: String
    ) : ChapterUi() {
        override fun map(mapper: TextMapper) = mapper.map(message)
    }

    object Progress : ChapterUi() {
        override fun map(mapper: TextMapper) = Unit
    }
}
