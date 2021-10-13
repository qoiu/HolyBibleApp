package com.qoiu.holybibleapp.domain.verses

import com.qoiu.holybibleapp.data.verses.VerseData
import com.qoiu.holybibleapp.data.verses.VerseDataToDomainMapper
import java.lang.Exception

class BaseVersesDataToDomainMapper(private val mapper: VerseDataToDomainMapper) :
    VersesDataToDomainMapper() {
    override fun map(data: List<VerseData>): VersesDomain =
        VersesDomain.Success(data.map { verseData ->
            verseData.map(mapper)
        })

override fun map(e: Exception): VersesDomain = VersesDomain.Fail(errorType(e))
}