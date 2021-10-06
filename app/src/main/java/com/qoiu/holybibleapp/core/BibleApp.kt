package com.qoiu.holybibleapp.core

import android.app.Application
import com.google.gson.Gson
import com.qoiu.holybibleapp.data.books.*
import com.qoiu.holybibleapp.data.books.cache.BooksCacheDataSource
import com.qoiu.holybibleapp.data.books.cache.BooksCacheMapper
import com.qoiu.holybibleapp.data.books.cloud.BookService
import com.qoiu.holybibleapp.data.books.cloud.BooksCloudDataSource
import com.qoiu.holybibleapp.data.books.cloud.BooksCloudMapper
import com.qoiu.holybibleapp.data.chapters.ChapterDataToDbMapper
import com.qoiu.holybibleapp.data.chapters.ChapterIdToUiMapper
import com.qoiu.holybibleapp.data.chapters.ChaptersRepository
import com.qoiu.holybibleapp.data.chapters.ToChapterMapper
import com.qoiu.holybibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.qoiu.holybibleapp.data.chapters.cache.ChaptersCacheMapper
import com.qoiu.holybibleapp.data.chapters.cloud.ChapterService
import com.qoiu.holybibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.qoiu.holybibleapp.data.chapters.cloud.ChaptersCloudMapper
import com.qoiu.holybibleapp.domain.book.BaseBookDataToDomainMapper
import com.qoiu.holybibleapp.domain.book.BaseBooksDataToDomainMapper
import com.qoiu.holybibleapp.domain.book.BooksInteractor
import com.qoiu.holybibleapp.domain.chapter.BaseChapterDataToDomainMapper
import com.qoiu.holybibleapp.domain.chapter.BaseChaptersDataToDomainMapper
import com.qoiu.holybibleapp.domain.chapter.ChaptersInteractor
import retrofit2.Retrofit
import com.qoiu.holybibleapp.presentation.*
import com.qoiu.holybibleapp.presentation.book.*
import com.qoiu.holybibleapp.presentation.chapter.BaseChapterDomainToUiMapper
import com.qoiu.holybibleapp.presentation.chapter.BaseChaptersDomainToUiMapper
import com.qoiu.holybibleapp.presentation.chapter.ChaptersCommunication
import com.qoiu.holybibleapp.presentation.chapter.ChaptersViewModel
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


class BibleApp : Application() {

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    lateinit var mainViewModel: MainViewModel
    lateinit var booksViewModel: BooksViewModel
    lateinit var chaptersViewModel: ChaptersViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val gson = Gson()
        val realmProvider = RealmProvider.Base()

        val client = OkHttpClient.Builder()
            .connectTimeout(1,TimeUnit.MINUTES)
            .readTimeout(1,TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
        val retrofit = Retrofit.Builder().client(client).baseUrl(BASE_URL).build()
        val service = retrofit.create(BookService::class.java)

        val cloudDataSource = BooksCloudDataSource.Base(service, Gson())
        val cacheDataSource = BooksCacheDataSource.Base(realmProvider, BookDataToDBMapper.Base())
        val toBookMapper = ToBookMapper.Base()
        val booksCloudMapper = BooksCloudMapper.Base(toBookMapper)
        val booksCacheMapper = BooksCacheMapper.Base(toBookMapper)
        val resourceProvider = ResourceProvider.Base(this)
        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper
        )
        val bookCache = BookCache.Base(this)
        val chaptersRepository = ChaptersRepository.Base(
            ChaptersCloudDataSource.Base(
                retrofit.create(ChapterService::class.java),
                gson
            ),
            ChaptersCacheDataSource.Base(realmProvider, ChapterDataToDbMapper.Base()),
            ChaptersCloudMapper.Base(ToChapterMapper.Cloud(bookCache)),
            ChaptersCacheMapper.Base(ToChapterMapper.Db(bookCache)),
            bookCache
        )

        val booksInteractor =
            BooksInteractor.Base(booksRepository, BaseBooksDataToDomainMapper(BaseBookDataToDomainMapper()))
        val chaptersInteractor = ChaptersInteractor.Base(
            chaptersRepository,
            BaseChaptersDataToDomainMapper(BaseChapterDataToDomainMapper())
        )

        val communication = BooksCommunication.Base()
        val navigator = Navigator.Base(this)
        val navigationCommunication = NavigationCommunication.Base()

        mainViewModel = MainViewModel(navigator, navigationCommunication)
        booksViewModel = BooksViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(resourceProvider,BaseBookDomainToUiMapper(resourceProvider)),
            communication,
            UiDataCache.Base(CollapsedIdCache.Base(this)),
            bookCache,
            navigator,
            navigationCommunication
        )
        chaptersViewModel = ChaptersViewModel(
            chaptersInteractor,
            ChaptersCommunication.Base(),
            BaseChaptersDomainToUiMapper(
                BaseChapterDomainToUiMapper(ChapterIdToUiMapper.Base(resourceProvider)),
                resourceProvider
            ),
            navigator,
            bookCache
        )
    }
}