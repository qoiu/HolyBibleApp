package com.qoiu.holybibleapp.domain.book

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.ResourceProvider
import com.qoiu.holybibleapp.presentation.book.BooksUi

abstract class BooksDomainToUiMapper(resourceProvider: ResourceProvider): Abstract.Mapper.DomainToUi.Base<List<BookDomain>,BooksUi>(resourceProvider)