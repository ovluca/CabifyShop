package com.example.cabifyshop.ui.main.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cabifyshop.R
import com.example.cabifyshop.ui.main.viewmodel.SplashScreenViewModel

class SplashScreenActivity : AppCompatActivity() {

    lateinit var viewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        viewModel = ViewModelProvider(this).get(SplashScreenViewModel::class.java)

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.products.observe(this, Observer {
            ShopActivity.open(this)
            finish()
        })
    }
}