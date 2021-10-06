package com.qoiu.holybibleapp.domain.book

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.domain.TestamentType
import com.qoiu.holybibleapp.presentation.book.BookUi

sealed class BookDomain: Abstract.Object<BookUi, BookDomainToUiMapper> {
    data class Base(
        private val id: Int, private val name: String
    ) : BookDomain() {
        override fun map(mapper: BookDomainToUiMapper): BookUi = mapper.map(id, name)
    }

    data class Testament (
        private val type: TestamentType
    ): BookDomain() {
        override fun map(mapper: BookDomainToUiMapper): BookUi = type.map(mapper)

    }
}

