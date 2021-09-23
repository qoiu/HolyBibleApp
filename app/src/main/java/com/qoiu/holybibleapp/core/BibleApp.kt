package com.qoiu.holybibleapp.core

import android.app.Application
import com.google.gson.Gson
import com.qoiu.holybibleapp.data.*
import com.qoiu.holybibleapp.data.cache.BooksCacheDataSource
import com.qoiu.holybibleapp.data.cache.BooksCacheMapper
import com.qoiu.holybibleapp.data.cache.RealmProvider
import com.qoiu.holybibleapp.data.net.BookService
import com.qoiu.holybibleapp.domain.BaseBookDataToDomainMapper
import retrofit2.Retrofit
import com.qoiu.holybibleapp.domain.BaseBooksDataToDomainMapper
import com.qoiu.holybibleapp.domain.BooksInteractor
import com.qoiu.holybibleapp.presentation.*
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


class BibleApp : Application() {

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    lateinit var mainViewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val client = OkHttpClient.Builder()
            .connectTimeout(1,TimeUnit.MINUTES)
            .readTimeout(1,TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
        val retrofit = Retrofit.Builder().client(client).baseUrl(BASE_URL).build()
        val service = retrofit.create(BookService::class.java)

        val cloudDataSource = BooksCloudDataSource.Base(service, Gson())
        val cacheDataSource = BooksCacheDataSource.Base(RealmProvider.Base(),BookDataToDBMapper.Base())
        val toBookMapper = ToBookMapper.Base()
        val booksCloudMapper = BooksCloudMapper.Base(toBookMapper)
        val booksCacheMapper = BooksCacheMapper.Base(toBookMapper)
        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper
        )
        val booksInteractor =
            BooksInteractor.Base(booksRepository, BaseBooksDataToDomainMapper(BaseBookDataToDomainMapper()))

        val communication = BooksCommunication.Base()
        mainViewModel = MainViewModel(
            communication,
            booksInteractor,
            BaseBooksDomainToUiMapper(ResourceProvider.Base(this),BaseBookDomainToUiMapper())
        )
    }
}