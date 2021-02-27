package com.mkpatir.showcase.api.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FeaturedModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("type") val type: String?,
    @SerializedName("cover") val coverPhoto: PhotoModel?,
    @SerializedName("title") val title: String?,
    @SerializedName("sub_title") val subtitle: String?
): Parcelable