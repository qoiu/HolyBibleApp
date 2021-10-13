package com.qoiu.holybibleapp.data.verses.cloud

import com.google.gson.annotations.SerializedName
import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.verses.ToVerseMapper
import com.qoiu.holybibleapp.data.verses.VerseData

data class VerseCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("verseId")
    private val verseId: Int,
    @SerializedName("verse")
    private val text: String
): Abstract.Object<VerseData, ToVerseMapper>{
    override fun map(mapper: ToVerseMapper) = mapper.map(id,verseId,text)
}