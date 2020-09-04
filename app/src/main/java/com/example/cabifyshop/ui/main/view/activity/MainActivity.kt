package com.example.cabifyshop.ui.main.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cabifyshop.R
import com.example.cabifyshop.ui.main.view.fragment.ProductsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ProductsFragment.newInstance())
                    .commitNow()
        }
    }
}