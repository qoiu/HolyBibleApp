package com.qoiu.holybibleapp.presentation.verses

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qoiu.holybibleapp.core.Read
import com.qoiu.holybibleapp.domain.verses.VersesDomainToUiMapper
import com.qoiu.holybibleapp.domain.verses.VersesInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VersesViewModel(
    private val versesInteractor: VersesInteractor,
    private val versesCommunication: VersesCommunication,
    private val versesMapper: VersesDomainToUiMapper,
    private val navigator: VersesNavigator,
    private val bookCache: Read<Pair<Int, String>>,
    private val chapterCache: Read<Int>,
) : ViewModel(){

    fun getTitle() = "${bookCache.read().second} Ch. ${chapterCache.read()}"

    fun observeVerses(owner: LifecycleOwner,observer: Observer<List<VerseUi>>) =
        versesCommunication.observe(owner, observer)

    fun fetchVerses(){
        versesCommunication.map(listOf(VerseUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val verses = versesInteractor.fetchVerses()
            val verseUi = verses.map(versesMapper)
            withContext(Dispatchers.Main){
                verseUi.map(versesCommunication)
            }
        }
    }

    fun init(){
        navigator.saveVersesScreen()
        fetchVerses()
    }

    fun getChapterName() = chapterCache.read()
}