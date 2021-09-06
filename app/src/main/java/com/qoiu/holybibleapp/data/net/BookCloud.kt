package com.qoiu.holybibleapp.data.net

import com.google.gson.annotations.SerializedName
import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.Book
import com.qoiu.holybibleapp.data.BookCloudMapper
import com.qoiu.holybibleapp.data.BooksCloudMapper

data class BookCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("name")
    private val name: String
) : Abstract.Object<Book, BookCloudMapper>() {
    override fun map(mapper: BookCloudMapper) = mapper.map(id, name)
}
