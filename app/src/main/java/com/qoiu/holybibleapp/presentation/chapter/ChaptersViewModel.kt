package com.qoiu.holybibleapp.presentation.chapter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qoiu.holybibleapp.core.Read
import com.qoiu.holybibleapp.core.Save
import com.qoiu.holybibleapp.domain.chapter.ChaptersDomainToUiMapper
import com.qoiu.holybibleapp.domain.chapter.ChaptersInteractor
import com.qoiu.holybibleapp.presentation.main.NavigationCommunication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChaptersViewModel(
    private val chaptersInteractor: ChaptersInteractor,
    private val chaptersCommunication: ChaptersCommunication,
    private val chapterMapper: ChaptersDomainToUiMapper,
    private val navigator: ChaptersNavigator,
    private val bookCache: Read<Pair<Int,String>>,
    private val chapterCache: Save<Int>,
    private val navigationCommunication: NavigationCommunication
) : ViewModel(), Show{

    fun observeChapters(owner: LifecycleOwner, observer: Observer<List<ChapterUi>>){
        chaptersCommunication.observe(owner, observer)
    }

    fun fetchChapters(){
        chaptersCommunication.map(listOf(ChapterUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val chapters = chaptersInteractor.fetchChapters()
            val chapterUi = chapters.map(chapterMapper)
            withContext(Dispatchers.Main){
                chapterUi.map(chaptersCommunication)
            }
        }
    }

    fun init(){
        navigator.saveChaptersScreen()
        fetchChapters()
    }

    fun getBookName() = bookCache.read().second
    override fun show(id: Int) {
        chapterCache.save(id)
        navigator.nextScreen(navigationCommunication)
    }

}

interface Show{
    fun show(id: Int)
}
