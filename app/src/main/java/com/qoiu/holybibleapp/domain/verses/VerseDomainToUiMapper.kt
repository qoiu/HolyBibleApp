package com.qoiu.holybibleapp.domain.verses

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.presentation.verses.VerseUi

interface VerseDomainToUiMapper : Abstract.Mapper {
    fun map(id: Int, text: String): VerseUi
}