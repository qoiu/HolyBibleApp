package com.qoiu.holybibleapp.presentation.verses

import com.qoiu.holybibleapp.core.ErrorType
import com.qoiu.holybibleapp.core.ResourceProvider
import com.qoiu.holybibleapp.domain.verses.VerseDomain
import com.qoiu.holybibleapp.domain.verses.VerseDomainToUiMapper
import com.qoiu.holybibleapp.domain.verses.VersesDomainToUiMapper

class BaseVersesDomainToUiMapper(
    private val mapper: VerseDomainToUiMapper,
    resProvider: ResourceProvider
): VersesDomainToUiMapper(resProvider) {
    override fun map(data: List<VerseDomain>): VersesUi = VersesUi(data.map {
        it.map(mapper)
    })

    override fun map(errorType: ErrorType): VersesUi  = VersesUi(listOf(VerseUi.Fail(errorMessage(errorType))))
}