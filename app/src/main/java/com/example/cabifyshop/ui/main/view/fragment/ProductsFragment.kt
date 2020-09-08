package com.example.cabifyshop.ui.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cabifyshop.databinding.ProductsFragmentBinding
import com.example.cabifyshop.ui.main.adapter.ProductsAdapter
import com.example.cabifyshop.ui.main.viewmodel.ProductsViewModel

class ProductsFragment : Fragment() {

    private var _binding: ProductsFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance() =
            ProductsFragment()
    }

    private lateinit var viewModel: ProductsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ProductsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getProducts().observe(viewLifecycleOwner, Observer { products ->
            binding.productsRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ProductsAdapter(products)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}