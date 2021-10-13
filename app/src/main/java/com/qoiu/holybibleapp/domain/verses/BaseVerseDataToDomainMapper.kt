package com.qoiu.holybibleapp.domain.verses

import com.qoiu.holybibleapp.data.verses.VerseDataToDomainMapper

class BaseVerseDataToDomainMapper: VerseDataToDomainMapper {
    override fun map(id: Int, text: String): VerseDomain = VerseDomain(id,text)
}