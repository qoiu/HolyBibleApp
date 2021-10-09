package com.qoiu.holybibleapp.sl

import com.qoiu.holybibleapp.data.chapters.ChapterDataToDbMapper
import com.qoiu.holybibleapp.data.chapters.ChapterIdToUiMapper
import com.qoiu.holybibleapp.data.chapters.ChaptersRepository
import com.qoiu.holybibleapp.data.chapters.ToChapterMapper
import com.qoiu.holybibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.qoiu.holybibleapp.data.chapters.cache.ChaptersCacheMapper
import com.qoiu.holybibleapp.data.chapters.cloud.ChapterService
import com.qoiu.holybibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.qoiu.holybibleapp.data.chapters.cloud.ChaptersCloudMapper
import com.qoiu.holybibleapp.domain.chapter.BaseChapterDataToDomainMapper
import com.qoiu.holybibleapp.domain.chapter.BaseChaptersDataToDomainMapper
import com.qoiu.holybibleapp.domain.chapter.ChaptersInteractor
import com.qoiu.holybibleapp.presentation.chapter.BaseChapterDomainToUiMapper
import com.qoiu.holybibleapp.presentation.chapter.BaseChaptersDomainToUiMapper
import com.qoiu.holybibleapp.presentation.chapter.ChaptersCommunication
import com.qoiu.holybibleapp.presentation.chapter.ChaptersViewModel

class ChaptersModule(private val coreModule: CoreModule): BaseModule<ChaptersViewModel> {
    override fun getViewModel(): ChaptersViewModel = ChaptersViewModel(
        getChaptersInteractor(),
        getChaptersCommunication(),
        getChaptersMapper(),
        coreModule.navigator,
        coreModule.bookCache
    )

    private fun getChaptersMapper() = BaseChaptersDomainToUiMapper(
            BaseChapterDomainToUiMapper(ChapterIdToUiMapper.Base(coreModule.resourceProvider)),
            coreModule.resourceProvider
        )

    private fun getChaptersCommunication() = ChaptersCommunication.Base()

    private fun getChaptersInteractor() = ChaptersInteractor.Base(
        getChaptersRepository(),
        BaseChaptersDataToDomainMapper(BaseChapterDataToDomainMapper())
    )

    private fun getChaptersRepository() = ChaptersRepository.Base(
        ChaptersCloudDataSource.Base(
            coreModule.makeService(ChapterService::class.java),
            coreModule.gson
        ),
        ChaptersCacheDataSource.Base(coreModule.realmProvider,ChapterDataToDbMapper.Base()),
        ChaptersCloudMapper.Base(ToChapterMapper.Cloud(coreModule.bookCache)),
        ChaptersCacheMapper.Base(ToChapterMapper.Db(coreModule.bookCache)),
        coreModule.bookCache
    )
}