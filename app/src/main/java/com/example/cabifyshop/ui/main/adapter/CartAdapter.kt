package com.example.cabifyshop.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.cabifyshop.R
import com.example.cabifyshop.data.model.ProductAndCart
import com.example.cabifyshop.databinding.RowCartBinding
import com.example.cabifyshop.ui.main.viewmodel.CartViewModel

class CartAdapter(private val list: List<ProductAndCart>, private val viewModel: CartViewModel) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

	class ViewHolder(private val itemBinding: RowCartBinding, private val viewModel: CartViewModel) : RecyclerView.ViewHolder(itemBinding.root) {
		private val context: Context = itemBinding.root.context

		fun bind(productAndCart: ProductAndCart) {
			itemBinding.productName.text = productAndCart.product.name
			itemBinding.productQuantity.text = context.getString(R.string._quantity, productAndCart.cart.quantity.toString())
			itemBinding.productPriceWithoutDiscount.text = context.getString(R.string._price, productAndCart.getTotalPrice().toString())
			itemBinding.productPriceWithDiscount.text = context.getString(R.string._price, productAndCart.applyDiscount().toString())

			itemBinding.quantitySpinner.isSelected = false
			itemBinding.quantitySpinner.setSelection(productAndCart.cart.quantity, false)

			itemBinding.quantitySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
				override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
					viewModel.updateQuantity(productCode = productAndCart.product.code, quantity = position)
				}

				override fun onNothingSelected(parent: AdapterView<*>?) {
				}
			}


		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val itemBinding = RowCartBinding.inflate(inflater, parent, false)
		return ViewHolder(itemBinding, viewModel)
	}

	override fun getItemCount(): Int {
		return list.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val product = list[position]
		holder.bind(product)
	}
}