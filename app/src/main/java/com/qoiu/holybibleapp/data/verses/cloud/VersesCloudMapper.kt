package com.qoiu.holybibleapp.data.verses.cloud

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.verses.ToVerseMapper
import com.qoiu.holybibleapp.data.verses.VerseData

interface VersesCloudMapper : Abstract.Mapper.Data<List<VerseCloud>,List<VerseData>>{
    class Base(private val mapper: ToVerseMapper): VersesCloudMapper{
        override fun map(data: List<VerseCloud>): List<VerseData> = data.map{verse->
            verse.map(mapper)
        }
    }
}