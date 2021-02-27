package com.mkpatir.showcase.api.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CollectionModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("definition") val definition: String?,
    @SerializedName("start") val startTime: String?,
    @SerializedName("end") val endTime: String?,
    @SerializedName("share_url") val shareUrl: String?,
    @SerializedName("cover") val coverPhoto: PhotoModel?,
    @SerializedName("logo") val logo: PhotoModel?
): Parcelable