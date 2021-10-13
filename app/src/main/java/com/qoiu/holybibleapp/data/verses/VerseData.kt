package com.qoiu.holybibleapp.data.verses

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.DbWrapper
import com.qoiu.holybibleapp.data.verses.cache.VerseDB
import com.qoiu.holybibleapp.domain.verses.VerseDomain

class VerseData(
    private val id: Int,
    private val verseId: Int,
    private val text: String):
Abstract.Object.ToDb<VerseDB, VerseDataToDbMapper>,
Abstract.Object<VerseDomain, VerseDataToDomainMapper>{
    override fun mapBy(mapper: VerseDataToDbMapper, db: DbWrapper<VerseDB>) = mapper.mapToDb(id, verseId, text, db)

    override fun map(mapper: VerseDataToDomainMapper): VerseDomain = mapper.map(verseId, text)
}
