package com.mkpatir.showcase.api.models

import com.google.gson.annotations.SerializedName

data class DiscoverResponseModel(
     @SerializedName("type") val type: String?,
     @SerializedName("title") val title: Float?,
     @SerializedName("featured") val featured: ArrayList<FeaturedModel>?,
     @SerializedName("products") val products: ArrayList<ProductModel>?,
     @SerializedName("categories") val categories: ArrayList<CategoryModel>?,
     @SerializedName("collections") val collections: ArrayList<CollectionModel>?,
     @SerializedName("shops") val shops: ArrayList<ShopModel>?
)