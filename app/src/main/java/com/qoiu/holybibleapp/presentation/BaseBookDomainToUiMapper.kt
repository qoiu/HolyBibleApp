package com.qoiu.holybibleapp.presentation

import com.qoiu.holybibleapp.domain.BookDomainToUiMapper

class BaseBookDomainToUiMapper : BookDomainToUiMapper {
    override fun map(id: Int, name: String): BookUi = BookUi.Base(id, name)
}