package com.qoiu.holybibleapp.presentation.book

import com.qoiu.holybibleapp.core.Communication

interface BooksCommunication : Communication<List<BookUi>>{

    class Base(): BooksCommunication, Communication.Base<List<BookUi>>()
}
