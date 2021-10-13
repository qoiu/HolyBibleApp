package com.qoiu.holybibleapp.data.verses

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.domain.verses.VerseDomain

interface VerseDataToDomainMapper : Abstract.Mapper {
    fun map(id: Int, text: String): VerseDomain
}
