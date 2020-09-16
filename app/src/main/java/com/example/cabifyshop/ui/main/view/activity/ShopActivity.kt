package com.example.cabifyshop.ui.main.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.cabifyshop.R
import com.example.cabifyshop.databinding.ShopActivityBinding
/**
 * Main Activity. Navigation Graph was implemented to create a smooth and easy replacement of fragments
 *
 * */
class ShopActivity : AppCompatActivity() {

    private lateinit var binding: ShopActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShopActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupMenu()
    }

    private fun setupMenu() {
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.cart -> {
                    binding.navHostFragment.findNavController().navigate(R.id.action_productsFragment_to_cartFragment)
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