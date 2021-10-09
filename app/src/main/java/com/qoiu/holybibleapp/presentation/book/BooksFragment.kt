package com.qoiu.holybibleapp.presentation.book

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.qoiu.holybibleapp.R
import com.qoiu.holybibleapp.core.BibleApp
import com.qoiu.holybibleapp.core.Retry
import com.qoiu.holybibleapp.presentation.BaseFragment

class BooksFragment : BaseFragment() {

    private val viewModelFactory by lazy {
        (requireActivity().application as BibleApp).booksFactory()
    }

    private val viewModel by activityViewModels<BooksViewModel> { viewModelFactory }


    override fun getTitle(): String = getString(R.string.app_name)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BookAdapter(
            object : Retry {
                override fun tryAgain() = viewModel.fetchBooks()
            },
            object : BookAdapter.CollapseListener {
                override fun collapseOrExpand(id: Int) = viewModel.collapseOrExpand(id)
            },
            object : BookAdapter.BookListener {
                override fun showBook(id: Int, name: String) = viewModel.showBook(id, name)
            }
        )
        recyclerView?.adapter = adapter
        viewModel.observe(this
        , {
            adapter.update(it)
        })
        viewModel.init()
    }

    override fun onPause() {
        super.onPause()
        viewModel.save()
    }
}