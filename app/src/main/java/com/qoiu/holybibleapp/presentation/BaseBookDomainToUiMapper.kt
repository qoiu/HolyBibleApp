package com.qoiu.holybibleapp.presentation

import com.qoiu.holybibleapp.R
import com.qoiu.holybibleapp.domain.BookDomainToUiMapper
import com.qoiu.holybibleapp.domain.TestamentType

class BaseBookDomainToUiMapper(private val resourceProvider: ResourceProvider) :
    BookDomainToUiMapper {
    override fun map(id: Int, name: String): BookUi = when {
        TestamentType.NEW.matches(id) ->
            BookUi.Testament(
            id,
            resourceProvider.getString(R.string.new_testament)
        )
        TestamentType.OLD.matches(id) ->
            BookUi.Testament(
            id,
            resourceProvider.getString(R.string.old_testament)
        )
        else ->
            BookUi.Base(id, name)
    }
}