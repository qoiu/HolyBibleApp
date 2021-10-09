package com.qoiu.holybibleapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qoiu.holybibleapp.R
import com.qoiu.holybibleapp.core.BibleApp

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as BibleApp).getMainViewModel()

        viewModel.observe(this, {
            val fragment = viewModel.getFragment(it)
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }
        )
        viewModel.init()
    }

    override fun onBackPressed() {
        if (viewModel.navigateBack())
            super.onBackPressed()
    }
}