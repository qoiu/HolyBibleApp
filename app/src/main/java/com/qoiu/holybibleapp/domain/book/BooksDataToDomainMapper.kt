package com.qoiu.holybibleapp.domain.book

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.books.BookData

abstract class BooksDataToDomainMapper: Abstract.Mapper.DataToDomain.Base<List<BookData>, BooksDomain>()