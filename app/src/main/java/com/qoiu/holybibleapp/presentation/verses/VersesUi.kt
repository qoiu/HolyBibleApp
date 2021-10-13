package com.qoiu.holybibleapp.presentation.verses

import com.qoiu.holybibleapp.core.Abstract

data class VersesUi(private val verses: List<VerseUi>): Abstract.Object<Unit,VersesCommunication>{

        override fun map(mapper: VersesCommunication) = mapper.map(verses)

}