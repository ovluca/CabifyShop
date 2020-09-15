package com.example.cabifyshop.data.api

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.cabifyshop.data.local.AppDatabase
import com.example.cabifyshop.data.repository.ApiRepository

/**
 *
 * This Worker is used to download products from server and populate the local database
 *
 * */

class FetchProductsWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {

	private val repository = ApiRepository()
	private val db = AppDatabase.getAppDataBase(appContext)

	override suspend fun doWork(): Result {
		return try {
			val products = repository.getProducts()
			db!!.productDao().insertAll(products)
			Result.success()
		} catch (error: Throwable) {
			Result.failure()
		}
	}
}