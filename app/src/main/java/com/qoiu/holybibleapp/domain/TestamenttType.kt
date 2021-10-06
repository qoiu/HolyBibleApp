package com.qoiu.holybibleapp.domain

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.Matcher
import com.qoiu.holybibleapp.domain.book.BookDomainToUiMapper
import com.qoiu.holybibleapp.presentation.book.BookUi

enum class TestamentType(private val id: Int): Matcher<Int>, Abstract.Object<BookUi, BookDomainToUiMapper>{
    OLD(Int.MIN_VALUE),
    NEW(Int.MAX_VALUE);

    override fun matches(arg: Int) = id==arg
    override fun map(mapper: BookDomainToUiMapper): BookUi = mapper.map(id,name)

}