package com.qoiu.holybibleapp.core

import android.app.Application
import com.qoiu.holybibleapp.BuildConfig.USE_MOCKS
import com.qoiu.holybibleapp.sl.*
import com.qoiu.holybibleapp.sl.books.BooksFactory
import com.qoiu.holybibleapp.sl.books.BooksModule
import com.qoiu.holybibleapp.sl.chapters.ChaptersFactory
import com.qoiu.holybibleapp.sl.chapters.ChaptersModule
import com.qoiu.holybibleapp.sl.verses.VersesFactory
import com.qoiu.holybibleapp.sl.verses.VersesModule


class BibleApp : Application() {

    private val coreModule = CoreModule()
    override fun onCreate() {
        super.onCreate()
        coreModule.init(this)
    }

    fun getMainViewModel() = coreModule.getViewModel()
    fun chaptersFactory() = ChaptersFactory(ChaptersModule(coreModule))
    fun booksFactory() = BooksFactory(BooksModule(coreModule, USE_MOCKS))
    fun versesFactory() = VersesFactory(VersesModule(coreModule))
}