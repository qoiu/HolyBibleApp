package com.qoiu.holybibleapp.sl.verses

import com.qoiu.holybibleapp.data.verses.ToVerseMapper
import com.qoiu.holybibleapp.data.verses.VerseDataToDbMapper
import com.qoiu.holybibleapp.data.verses.VersesRepository
import com.qoiu.holybibleapp.data.verses.cache.VersesCacheDataSource
import com.qoiu.holybibleapp.data.verses.cache.VersesCacheMapper
import com.qoiu.holybibleapp.data.verses.cloud.VersesCloudDataSource
import com.qoiu.holybibleapp.data.verses.cloud.VersesCloudMapper
import com.qoiu.holybibleapp.data.verses.cloud.VersesService
import com.qoiu.holybibleapp.domain.verses.BaseVerseDataToDomainMapper
import com.qoiu.holybibleapp.domain.verses.BaseVersesDataToDomainMapper
import com.qoiu.holybibleapp.domain.verses.VersesInteractor
import com.qoiu.holybibleapp.presentation.verses.BaseVerseDomainToUiMapper
import com.qoiu.holybibleapp.presentation.verses.BaseVersesDomainToUiMapper
import com.qoiu.holybibleapp.presentation.verses.VersesCommunication
import com.qoiu.holybibleapp.presentation.verses.VersesViewModel
import com.qoiu.holybibleapp.sl.BaseModule
import com.qoiu.holybibleapp.sl.CoreModule

class VersesModule(private val coreModule: CoreModule): BaseModule<VersesViewModel> {
    override fun getViewModel(): VersesViewModel = VersesViewModel(
        getVersesInteractor(),
        getVersesCommunication(),
        getVersesMapper(),
        coreModule.navigator,
        coreModule.bookCache,
        coreModule.chapterCache
    )

    private fun getVersesMapper() = BaseVersesDomainToUiMapper(
        BaseVerseDomainToUiMapper(),
        coreModule.resourceProvider
    )

    private fun getVersesCommunication() = VersesCommunication.Base()

    private fun getVersesInteractor() = VersesInteractor.Base(
        getVersesRepository(),
        BaseVersesDataToDomainMapper(BaseVerseDataToDomainMapper())
    )

    private fun getVersesRepository()= VersesRepository.Base(
        VersesCloudDataSource.Base(
            coreModule.makeService(VersesService::class.java),
            coreModule.gson
        ),
        VersesCacheDataSource.Base(coreModule.realmProvider,VerseDataToDbMapper.Base()),
        VersesCloudMapper.Base(ToVerseMapper.Base()),
        VersesCacheMapper.Base(ToVerseMapper.Base()),
        coreModule.bookCache,
        coreModule.chapterCache
    )
}