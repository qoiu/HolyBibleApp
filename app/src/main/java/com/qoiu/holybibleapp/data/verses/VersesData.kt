package com.qoiu.holybibleapp.data.verses

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.domain.verses.VersesDataToDomainMapper
import com.qoiu.holybibleapp.domain.verses.VersesDomain
import java.lang.Exception

sealed class VersesData : Abstract.Object<VersesDomain, VersesDataToDomainMapper> {

    data class Success(private val verses: List<VerseData>): VersesData(){
        override fun map(mapper: VersesDataToDomainMapper) = mapper.map(verses)
    }

    data class Fail(private val exception: Exception): VersesData(){
        override fun map(mapper: VersesDataToDomainMapper): VersesDomain = mapper.map(exception)

    }

}
