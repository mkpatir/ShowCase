package com.mkpatir.showcase.api.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("code") val code: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("slug") val slug: String?,
    @SerializedName("definition") val definition: String?,
    @SerializedName("old_price") val oldPrice: Int?,
    @SerializedName("price") val price: Int?,
    @SerializedName("stock") val stock: Int?,
    @SerializedName("max_installment") val maxInstallment: Int?,
    @SerializedName("commission_rate") val commissionRate: Int?,
    @SerializedName("cargo_time") val cargoTime: Int?,
    @SerializedName("is_cargo_free") val isCargoFree: Boolean?,
    @SerializedName("is_new") val isNew: Boolean?,
    @SerializedName("reject_reason") val rejectReason: String?,
    @SerializedName("category_id") val categoryId: Int?,
    @SerializedName("difference") val difference: String?,
    @SerializedName("is_editor_choice") val isEditorChoice: Boolean?,
    @SerializedName("comment_count") val commentCount: Int?,
    @SerializedName("is_owner") val isOwner: Boolean?,
    @SerializedName("is_approved") val isApproved: Boolean?,
    @SerializedName("is_active") val isActive: Boolean?,
    @SerializedName("share_url") val shareUrl: String?,
    @SerializedName("is_liked") val isLiked: Boolean?,
    @SerializedName("like_count") val likeCount: Int?,
    @SerializedName("shop") val shop: ShopModel?,
    @SerializedName("category") val category: ParentCategoryModel?,
    @SerializedName("images") val images: ArrayList<PhotoModel>?
): Parcelable

@Parcelize
data class ShopModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("slug") val slug: String?,
    @SerializedName("definition") val definition: String?,
    @SerializedName("name_updateable") val nameUpdateable: Boolean?,
    @SerializedName("vacation_mode") val vacationMode: Int?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("shop_payment_id") val shopPaymentId: Int?,
    @SerializedName("popular_products") val popularProducts: ArrayList<ProductModel>?,
    @SerializedName("product_count") val productCount: Int?,
    @SerializedName("shop_rate") val shopRate: Int?,
    @SerializedName("comment_count") val commentCount: Int?,
    @SerializedName("follower_count") val followerCount: Int?,
    @SerializedName("is_editor_choice") val isEditorChoice: Boolean?,
    @SerializedName("is_following") val isFollowing: Boolean?,
    @SerializedName("cover") val coverPhoto: PhotoModel?,
    @SerializedName("share_url") val shareUrl: String?,
    @SerializedName("logo") val logo: PhotoModel?
): Parcelable