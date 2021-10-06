package com.qoiu.holybibleapp.presentation.chapter

import android.os.Bundle
import android.view.View
import com.qoiu.holybibleapp.core.BibleApp
import com.qoiu.holybibleapp.core.Retry
import com.qoiu.holybibleapp.presentation.BaseFragment

class ChaptersFragment : BaseFragment() {
    private lateinit var viewModel: ChaptersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity().application as BibleApp).chaptersViewModel
    }

    override fun getTitle(): String = viewModel.getBookName()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ChaptersAdapter(object : Retry{
            override fun tryAgain() = viewModel.fetchChapters()
        })

        viewModel.observeChapters(this,{adapter.update(it)})
        recyclerView?.adapter = adapter
        viewModel.init()
    }
}