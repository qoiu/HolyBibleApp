package com.qoiu.holybibleapp.presentation.verses

import com.qoiu.holybibleapp.core.ComparableTextMapper
import com.qoiu.holybibleapp.core.TextMapper

sealed class VerseUi : ComparableTextMapper<VerseUi> {
    class Base(private val text: String) : VerseUi() {
        override fun map(mapper: TextMapper) = mapper.map(text)
    }
    class Fail(private val message: String): VerseUi(){
        override fun map(mapper: TextMapper) = mapper.map(message)
    }
    object Progress: VerseUi(){
        override fun map(mapper: TextMapper) = Unit
    }
}
