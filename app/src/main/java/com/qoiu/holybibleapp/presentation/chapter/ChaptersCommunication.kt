package com.qoiu.holybibleapp.presentation.chapter

import com.qoiu.holybibleapp.core.Communication

interface ChaptersCommunication : Communication<List<ChapterUi>>{

    class Base(): ChaptersCommunication, Communication.Base<List<ChapterUi>>()
}
