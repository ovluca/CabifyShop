package com.example.cabifyshop.ui.main.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cabifyshop.R
import com.example.cabifyshop.ui.main.view.fragment.ProductsFragment

class ShopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductsFragment.newInstance())
                .commitNow()
        }
    }

    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, ShopActivity::class.java))
        }
    }
}