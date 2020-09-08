package com.example.cabifyshop.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cabifyshop.data.model.Product
import com.example.cabifyshop.databinding.RowProductBinding

class ProductsAdapter(private val list: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {


	class ProductViewHolder(private val itemBinding: RowProductBinding) : RecyclerView.ViewHolder(itemBinding.root) {
		fun bind(product: Product) {
			itemBinding.productName.text = product.name
			itemBinding.productPrice.text = product.price.toString()
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