package com.example.cabifyshop.ui.main.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cabifyshop.R
import com.example.cabifyshop.databinding.ShopActivityBinding
import com.example.cabifyshop.ui.main.view.fragment.CartFragment
import com.example.cabifyshop.ui.main.view.fragment.ProductsFragment

class ShopActivity : AppCompatActivity() {

    private lateinit var binding: ShopActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShopActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductsFragment.newInstance())
                .commitNow()
        }
        setupMenu()
    }

    private fun setupMenu() {
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.cart -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, CartFragment.newInstance())
                        .commitNow()
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, ShopActivity::class.java))
        }
    }
}