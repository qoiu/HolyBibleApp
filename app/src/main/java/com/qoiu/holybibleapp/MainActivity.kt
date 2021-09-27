package com.qoiu.holybibleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.qoiu.holybibleapp.core.BibleApp
import com.qoiu.holybibleapp.presentation.BibleAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = (application as BibleApp).mainViewModel
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = BibleAdapter(object : BibleAdapter.BibleViewHolder.Retry{
            override fun tryAgain() {
                viewModel.fetchBooks()
            }
        })
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))

        viewModel.observeSuccess(this, {
            adapter.update(it)
        })
        viewModel.fetchBooks()
    }
}