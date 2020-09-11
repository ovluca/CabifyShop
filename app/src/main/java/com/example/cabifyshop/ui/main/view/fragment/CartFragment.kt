package com.example.cabifyshop.ui.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cabifyshop.databinding.CartFragmentBinding
import com.example.cabifyshop.ui.main.adapter.CartAdapter
import com.example.cabifyshop.ui.main.viewmodel.CartViewModel

class CartFragment : Fragment() {

	private lateinit var viewModel: CartViewModel
	private var _binding: CartFragmentBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_binding = CartFragmentBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
		setupObservers()
	}

	private fun setupObservers() {
		viewModel.getCart().observe(viewLifecycleOwner, Observer { products ->
			binding.cartRecyclerView.apply {
				layoutManager = LinearLayoutManager(context)
				adapter = CartAdapter(products)
			}
		})
	}


	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}
}