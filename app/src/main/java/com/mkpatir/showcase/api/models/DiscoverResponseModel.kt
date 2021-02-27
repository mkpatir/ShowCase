package com.mkpatir.showcase.api.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DiscoverResponseModel(
     @SerializedName("type") val type: String?,
     @SerializedName("title") val title: String?,
     @SerializedName("featured") val featured: ArrayList<FeaturedModel>?,
     @SerializedName("products") val products: ArrayList<ProductModel>?,
     @SerializedName("categories") val categories: ArrayList<CategoryModel>?,
     @SerializedName("collections") val collections: ArrayList<CollectionModel>?,
     @SerializedName("shops") val shops: ArrayList<ShopModel>?
): Parcelable