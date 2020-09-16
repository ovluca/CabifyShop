package com.example.cabifyshop.data.api

import com.example.cabifyshop.data.model.Product

/**
 * data class for getting response from retrofit
 *
 * No error handling was added, mainly reason it's only a request and I already added internet connection check.
 * */
data class Response(val products: List<Product>)

