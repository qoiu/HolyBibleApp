package com.qoiu.holybibleapp.data.net

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.BookData

interface BookServerToDataMapper: Abstract.Mapper {

    fun map(id: Int, name: String): BookData
}
