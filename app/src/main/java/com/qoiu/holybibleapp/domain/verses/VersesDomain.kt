package com.qoiu.holybibleapp.domain.verses

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.ErrorType
import com.qoiu.holybibleapp.presentation.verses.VersesUi

sealed class VersesDomain: Abstract.Object<VersesUi, VersesDomainToUiMapper> {
    data class Success(private val verses: List<VerseDomain>): VersesDomain() {
        override fun map(mapper: VersesDomainToUiMapper): VersesUi = mapper.map(verses)
    }

    data class Fail(private val e: ErrorType): VersesDomain(){
        override fun map(mapper: VersesDomainToUiMapper): VersesUi = mapper.map(e)
    }
}
