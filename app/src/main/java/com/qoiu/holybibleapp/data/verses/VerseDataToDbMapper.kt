package com.qoiu.holybibleapp.data.verses

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.DbWrapper
import com.qoiu.holybibleapp.data.verses.cache.VerseDB

interface VerseDataToDbMapper : Abstract.Mapper {
    fun mapToDb(id: Int, verseId: Int, text: String, db: DbWrapper<VerseDB>): VerseDB

    class Base : VerseDataToDbMapper {
        override fun mapToDb(id: Int, verseId: Int, text: String, db: DbWrapper<VerseDB>) =
            db.createObject(id).apply {
                this.verseId = verseId
                this.text = text
            }
    }

}
