package com.qoiu.holybibleapp.data.chapters.cache

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.chapters.ChapterData
import com.qoiu.holybibleapp.data.chapters.ToChapterMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ChapterDb : RealmObject(), Abstract.Object<ChapterData,ToChapterMapper>{


    @PrimaryKey
    var id: Int = -1


    override fun map(mapper: ToChapterMapper): ChapterData = mapper.map(id)
}