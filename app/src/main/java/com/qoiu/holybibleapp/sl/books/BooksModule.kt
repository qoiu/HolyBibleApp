package com.qoiu.holybibleapp.sl.books

import com.qoiu.holybibleapp.data.books.BookDataToDBMapper
import com.qoiu.holybibleapp.data.books.BooksRepository
import com.qoiu.holybibleapp.data.books.ToBookMapper
import com.qoiu.holybibleapp.data.books.cache.BooksCacheDataSource
import com.qoiu.holybibleapp.data.books.cache.BooksCacheMapper
import com.qoiu.holybibleapp.data.books.cloud.BookService
import com.qoiu.holybibleapp.data.books.cloud.BooksCloudDataSource
import com.qoiu.holybibleapp.data.books.cloud.BooksCloudMapper
import com.qoiu.holybibleapp.domain.book.BaseBookDataToDomainMapper
import com.qoiu.holybibleapp.domain.book.BaseBooksDataToDomainMapper
import com.qoiu.holybibleapp.domain.book.BooksInteractor
import com.qoiu.holybibleapp.presentation.book.*
import com.qoiu.holybibleapp.sl.BaseModule
import com.qoiu.holybibleapp.sl.CoreModule

class BooksModule(
    private val coreModule: CoreModule,
    private val useMock: Boolean
) : BaseModule<BooksViewModel> {
    override fun getViewModel(): BooksViewModel {
        val uiDataCache = getBooksUiDataCache()
        return BooksViewModel(
            getBooksInteractor(),
            getBooksMapper(uiDataCache),
            getBooksCommunication(),
            getBooksUiDataCache(),
            coreModule.bookCache,
            coreModule.navigator,
            coreModule.navigationCommunication
        )
    }


    private fun getBooksInteractor() = BooksInteractor.Base(
        getBooksRepository(), BaseBooksDataToDomainMapper(BaseBookDataToDomainMapper())
    )

    private fun getBooksRepository(): BooksRepository {
        val toBookMapper = ToBookMapper.Base()
        return if (useMock)
            BooksRepository.Mock(
                getMockBooksCloudDataSource(),
                BooksCloudMapper.Base(toBookMapper)
            )
        else
            BooksRepository.Base(
                getBooksCloudDataSource(),
                BooksCacheDataSource.Base(coreModule.realmProvider, BookDataToDBMapper.Base()),
                BooksCloudMapper.Base(toBookMapper),
                BooksCacheMapper.Base(toBookMapper)
            )
    }

    fun getBooksCloudDataSource() = BooksCloudDataSource.Base(getBookService(), coreModule.gson)

    fun getMockBooksCloudDataSource() =
        BooksCloudDataSource.Mock(coreModule.resourceProvider, coreModule.gson)

    private fun getBookService() = coreModule.makeService(BookService::class.java)

    private fun getBooksMapper(uiDataCache: UiDataCache) = BaseBooksDomainToUiMapper(
        coreModule.resourceProvider,
        BaseBookDomainToUiMapper(coreModule.resourceProvider),
        uiDataCache
    )

    private fun getBooksUiDataCache() = UiDataCache.Base(
        if (useMock)
            CollapsedIdCache.Mock(coreModule.resourceProvider)
        else
            CollapsedIdCache.Base(coreModule.resourceProvider)
    )

    private fun getBooksCommunication() = BooksCommunication.Base()
}