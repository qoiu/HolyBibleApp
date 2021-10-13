package com.qoiu.holybibleapp.data.verses.cache

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.verses.ToVerseMapper
import com.qoiu.holybibleapp.data.verses.VerseData
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class VerseDB: RealmObject(), Abstract.Object<VerseData, ToVerseMapper> {

    @PrimaryKey
    var id: Int=-1
    var verseId: Int =-1
    var text = ""


    override fun map(mapper: ToVerseMapper) = mapper.map(id,verseId,text)
}