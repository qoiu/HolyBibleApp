package com.qoiu.holybibleapp.domain.verses

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.presentation.verses.VerseUi

data class VerseDomain(
    private val id: Int,
    private val text: String
) : Abstract.Object<VerseUi,VerseDomainToUiMapper>{
    override fun map(mapper: VerseDomainToUiMapper): VerseUi = mapper.map(id,text)
}
