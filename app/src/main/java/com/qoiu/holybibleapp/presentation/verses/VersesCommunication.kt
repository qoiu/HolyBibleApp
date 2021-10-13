package com.qoiu.holybibleapp.presentation.verses

import com.qoiu.holybibleapp.core.Communication

interface VersesCommunication: Communication<List<VerseUi>> {

    class Base(): VersesCommunication, Communication.Base<List<VerseUi>>()
}
