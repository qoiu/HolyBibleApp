package com.qoiu.holybibleapp.presentation.chapter

import com.qoiu.holybibleapp.core.ComparableTextMapper
import com.qoiu.holybibleapp.core.TextMapper

sealed class ChapterUi : ComparableTextMapper<ChapterUi>{

    class Base(
        private val id: Int, // TODO: 04.10.2021 Use to get verses
        private val text: String
    ) : ChapterUi() {
        override fun map(mapper: TextMapper) = mapper.map(text)
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
