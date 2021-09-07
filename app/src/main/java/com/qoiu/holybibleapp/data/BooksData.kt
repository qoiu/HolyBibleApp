package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.domain.BookDomain

sealed class BooksData: Abstract.Object<BookDomain, BooksDataToDomainMapper>() {

}