package com.qoiu.holybibleapp.data.verses.cache

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.verses.ToVerseMapper
import com.qoiu.holybibleapp.data.verses.VerseData

interface VersesCacheMapper: Abstract.Mapper.Data <List<VerseDB>,List<VerseData>>{
    class Base(private val mapper: ToVerseMapper): VersesCacheMapper{
        override fun map(data: List<VerseDB>): List<VerseData> = data.map { verseDB ->
            verseDB.map(mapper)
        }
    }
}