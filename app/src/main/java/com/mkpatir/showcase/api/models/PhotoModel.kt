package com.mkpatir.showcase.api.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoModel(
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("url") val url: String?,
    @SerializedName("medium") val mediumPhoto: PhotoItemModel?,
    @SerializedName("thumbnail") val thumbnailPhoto: PhotoItemModel?
): Parcelable

@Parcelize
data class PhotoItemModel(
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("url") val url: String?
): Parcelable