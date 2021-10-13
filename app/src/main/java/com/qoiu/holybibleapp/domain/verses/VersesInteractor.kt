package com.qoiu.holybibleapp.domain.verses

import com.qoiu.holybibleapp.data.verses.VersesRepository

interface VersesInteractor {

    suspend fun fetchVerses(): VersesDomain

    class Base(
        private val repository: VersesRepository,
        private val mapper: VersesDataToDomainMapper
    ): VersesInteractor{
        override suspend fun fetchVerses() = repository.fetchVerses().map(mapper)
    }
}