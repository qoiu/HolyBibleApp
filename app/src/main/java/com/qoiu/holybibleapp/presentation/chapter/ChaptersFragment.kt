package com.qoiu.holybibleapp.presentation.chapter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.qoiu.holybibleapp.core.BibleApp
import com.qoiu.holybibleapp.core.Retry
import com.qoiu.holybibleapp.presentation.BaseFragment

class ChaptersFragment : BaseFragment() {

    private val viewModelFactory by lazy {
        (requireActivity().application as BibleApp).chaptersFactory()
    }

    private val viewModel by viewModels<ChaptersViewModel>{viewModelFactory}

    override fun getTitle(): String = viewModel.getBookName()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ChaptersAdapter(object : Retry{
            override fun tryAgain() = viewModel.fetchChapters()
        },
        object : ChaptersAdapter.ChapterListener{
            override fun show(chapterUi: ChapterUi) = chapterUi.open(viewModel)
        })

        viewModel.observeChapters(this,{adapter.update(it)})
        recyclerView?.adapter = adapter
        viewModel.init()
    }
}