package com.example.cabifyshop.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cabifyshop.R
import com.example.cabifyshop.data.model.Product
import com.example.cabifyshop.databinding.RowProductBinding
import com.google.android.material.snackbar.Snackbar

class ProductsAdapter(private val list: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {


	class ProductViewHolder(private val itemBinding: RowProductBinding) : RecyclerView.ViewHolder(itemBinding.root) {

		private val context: Context = itemBinding.root.context

		fun bind(product: Product) {
			itemBinding.productName.text = product.name
			itemBinding.productPrice.text = context.getString(R.string._price, product.price.toString())

			itemBinding.addToCartButton.setOnClickListener { view ->
				run {
					Snackbar.make(view, context.getString(R.string._added_to_cart, product.name), Snackbar.LENGTH_SHORT).show()
				}
			}
		}

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val itemBinding = RowProductBinding.inflate(inflater, parent, false)
		return ProductViewHolder(itemBinding)
	}

	override fun getItemCount(): Int {
		return list.size
	}

	override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
		val product = list[position]
		holder.bind(product)
	}
}