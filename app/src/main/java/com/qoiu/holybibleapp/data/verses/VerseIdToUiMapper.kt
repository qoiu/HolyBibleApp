package com.qoiu.holybibleapp.data.verses

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.ResourceProvider
import com.qoiu.holybibleapp.presentation.verses.VerseUi

interface VerseIdToUiMapper: Abstract.Mapper {

    fun map(generatedId: Int, text: String): VerseUi

    class Base(private val resourceProvider: ResourceProvider): VerseIdToUiMapper{
        override fun map(generatedId: Int, text: String): VerseUi =
            VerseUi.Base(text)
    }
}
