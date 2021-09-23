package com.qoiu.holybibleapp.domain

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.presentation.BookUi

class BookDomain(private val id: Int, private val name: String) : Abstract.Object<BookUi, BookDomainToUiMapper> {
    override fun map(mapper: BookDomainToUiMapper): BookUi = mapper.map(id,name)
}