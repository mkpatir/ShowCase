package com.mkpatir.showcase.api.models

import com.google.gson.annotations.SerializedName

data class PhotoModel(
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("url") val url: String?,
    @SerializedName("medium") val mediumPhoto: PhotoItemModel?,
    @SerializedName("thumbnail") val thumbnailPhoto: PhotoItemModel?
)

data class PhotoItemModel(
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("url") val url: String?
)