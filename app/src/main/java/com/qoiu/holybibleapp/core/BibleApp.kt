package com.qoiu.holybibleapp.core

import android.app.Application
import com.qoiu.holybibleapp.BuildConfig.USE_MOCKS
import com.qoiu.holybibleapp.sl.*


class BibleApp : Application() {

    private val coreModule = CoreModule()
    override fun onCreate() {
        super.onCreate()
        coreModule.init(this)
    }

    fun getMainViewModel() = coreModule.getViewModel()
    fun chaptersFactory() = ChaptersFactory(ChaptersModule(coreModule))
    fun booksFactory() = BooksFactory(BooksModule(coreModule, USE_MOCKS))
}