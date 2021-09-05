package com.qoiu.holybibleapp.domain

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.presentation.BookUi

sealed class BookDomain: Abstract.Object<BookUi,Abstract.Mapper.Empty>() {
    class Success: BookDomain(){
        override fun map(mapper: Abstract.Mapper.Empty): BookUi {
            TODO("Not yet implemented")
        }
    }

    class Fail(private val errorType: Int): BookDomain(){
        override fun map(mapper: Abstract.Mapper.Empty): BookUi {
            TODO("Not yet implemented")
        }
    }
}