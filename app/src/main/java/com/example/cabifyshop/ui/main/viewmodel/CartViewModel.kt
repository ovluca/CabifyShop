package com.example.cabifyshop.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cabifyshop.data.local.AppDatabase
import com.example.cabifyshop.data.model.ProductAndCart
import com.example.cabifyshop.data.repository.LocalRepository

class CartViewModel(application: Application) : AndroidViewModel(application) {

	private var localRepository: LocalRepository = LocalRepository(AppDatabase.getAppDataBase(application)!!.productDao());

	fun getCart(): LiveData<List<ProductAndCart>> {
		return localRepository.getCart()
	}

}