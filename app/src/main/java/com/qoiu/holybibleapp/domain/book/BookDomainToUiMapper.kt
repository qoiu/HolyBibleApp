package com.qoiu.holybibleapp.domain.book

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.presentation.book.BookUi

interface BookDomainToUiMapper : Abstract.Mapper {
    fun map(id: Int, name: String): BookUi
}