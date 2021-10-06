package com.qoiu.holybibleapp.data.chapters.cloud

import com.google.gson.annotations.SerializedName
import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.chapters.ChapterData
import com.qoiu.holybibleapp.data.chapters.ToChapterMapper

data class ChapterCloud(

    @SerializedName("id")
    private val id: Int
) : Abstract.Object<ChapterData, ToChapterMapper> {
    override fun map(mapper: ToChapterMapper) =  mapper.map(id)
}