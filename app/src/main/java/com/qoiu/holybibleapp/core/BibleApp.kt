package com.qoiu.holybibleapp.core

import android.app.Application
import com.qoiu.holybibleapp.data.BookCloudMapper
import com.qoiu.holybibleapp.data.BooksCloudDataSource
import com.qoiu.holybibleapp.data.BooksCloudMapper
import com.qoiu.holybibleapp.data.BooksRepository
import com.qoiu.holybibleapp.data.cache.BookCacheMapper
import com.qoiu.holybibleapp.data.cache.BooksCacheDataSource
import com.qoiu.holybibleapp.data.cache.BooksCacheMapper
import com.qoiu.holybibleapp.data.cache.RealmProvider
import com.qoiu.holybibleapp.data.net.BookService
import retrofit2.Retrofit
import com.qoiu.holybibleapp.domain.BaseBookDataToDomainMapper
import com.qoiu.holybibleapp.domain.BooksInteractor


class BibleApp: Application() {

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    lateinit var mainViewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).build()
        val service = retrofit.create(BookService::class.java)

        val cloudDataSource = BooksCloudDataSource.Base(service)
        val cacheDataSource = BooksCacheDataSource.Base(RealmProvider.Base())
        val booksCloudMapper = BooksCloudMapper.Base(BookCloudMapper.Base())
        val booksCacheMapper = BooksCacheMapper.Base(BookCacheMapper.Base())
        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper
        )
        val booksInteractor = BooksInteractor.Base(booksRepository,BaseBookDataToDomainMapper())

        val booksInteractor: BooksInteractor = TODO()
        mainViewModel = MainViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(BooksCommunication.Base(), ResourceProvider.Base(this))
        )
    }
}