package com.example.rxjava

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getMaybelProducts() = retrofitService.getMaybelProducts()
}