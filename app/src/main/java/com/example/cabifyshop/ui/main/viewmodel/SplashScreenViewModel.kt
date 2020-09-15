package com.example.cabifyshop.ui.main.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cabifyshop.data.local.AppDatabase
import kotlinx.coroutines.launch

class SplashScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getAppDataBase(application)

    /**
     * Check if database contains products
     * */
    fun databaseHasProducts(onSuccess: (hasProducts: Boolean) -> Unit) {
        viewModelScope.launch {
            onSuccess(db!!.productDao().hasProducts())
        }
    }

    /**
     * Check internet connection
     * */
    fun isNetworkConnected(context: Context): Boolean {
        //1
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //2
        val activeNetwork = connectivityManager.activeNetwork
        //3
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        //4
        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}