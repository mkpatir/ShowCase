package com.mkpatir.showcase.api.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("parent_id") val parentId: Int?,
    @SerializedName("order") val order: Int?,
    @SerializedName("parent_category") val parentCategory: ParentCategoryModel?,
    @SerializedName("logo") val logo: PhotoModel?,
    @SerializedName("cover") val coverPhoto: PhotoModel?,
    @SerializedName("children") val children: ArrayList<CategoryModel>?
): Parcelable

@Parcelize
data class ParentCategoryModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("parent_id") val parentId: Int?,
    @SerializedName("order") val order: Int?,
    @SerializedName("parent_category") val parentCategory: ParentCategoryModel?
): Parcelable