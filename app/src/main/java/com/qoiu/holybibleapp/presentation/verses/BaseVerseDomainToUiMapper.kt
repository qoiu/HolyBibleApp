package com.qoiu.holybibleapp.presentation.verses

import com.qoiu.holybibleapp.domain.verses.VerseDomainToUiMapper

class BaseVerseDomainToUiMapper(): VerseDomainToUiMapper {
    override fun map(id: Int, text: String): VerseUi = VerseUi.Base("$id. $text")
}