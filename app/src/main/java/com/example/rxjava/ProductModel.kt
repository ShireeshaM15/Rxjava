package com.example.rxjava

import com.google.gson.annotations.SerializedName

data class ProductModel (

//    @SerializedName("id")
//    var id: Int? = null,
    @SerializedName("brand")
    var brand : String? = null,
    @SerializedName("name")
    var name: String?= null,
    @SerializedName("price")
    var price : String?= null,
    @SerializedName("product_link")
    var productLink: String?= null)
