package com.example.cabifyshop.ui.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cabifyshop.R
import com.example.cabifyshop.data.model.ProductAndCart
import com.example.cabifyshop.databinding.CartFragmentBinding
import com.example.cabifyshop.ui.main.adapter.CartAdapter
import com.example.cabifyshop.ui.main.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.shop_activity.*

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
		setupClickListeners()
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		setupToolbar()
	}

	private fun setupToolbar() {
		requireActivity().topAppBar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
		requireActivity().topAppBar.title = getString(R.string.cart)

		requireActivity().topAppBar.setNavigationOnClickListener {
			run {
				requireActivity().onBackPressed()
			}
		}

		requireActivity().topAppBar.menu.setGroupVisible(R.id.menu, false)
	}

	private fun setupObservers() {
		viewModel.getCart().observe(viewLifecycleOwner, Observer { products ->
			setupViewItems(products)
		})
	}

	private fun setupViewItems(products: List<ProductAndCart>) {
		binding.noItemsText.visibility = if (products.isEmpty()) View.VISIBLE else View.GONE

		binding.cartRecyclerView.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = CartAdapter(products)
			binding.totalToPayText.text = getString(R.string._total, viewModel.getTotal(products).toString())
		}
	}

	private fun setupClickListeners() {
		binding.checkoutButton.setOnClickListener {
			run {
				viewModel.deleteDataFromCart()
			}
		}
	}


	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}
}