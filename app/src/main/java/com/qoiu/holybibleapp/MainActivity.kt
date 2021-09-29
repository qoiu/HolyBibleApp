package com.qoiu.holybibleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.qoiu.holybibleapp.core.BibleApp
import com.qoiu.holybibleapp.presentation.BibleAdapter
import com.qoiu.holybibleapp.presentation.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (application as BibleApp).mainViewModel
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = BibleAdapter(object : BibleAdapter.BibleViewHolder.Retry {
            override fun tryAgain() {
                viewModel.fetchBooks()
            }
        },
            object : BibleAdapter.BibleViewHolder.CollapseListener {
                override fun collapseOrExpand(id: Int) {
                    viewModel.collapseOrExpand(id)
                }
            })
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        viewModel.observeSuccess(this,
            {
                adapter.update(it)
            })
        viewModel.fetchBooks()
    }

    override fun onPause() {
        viewModel.saveCollapsedStates()
        super.onPause()
    }
}